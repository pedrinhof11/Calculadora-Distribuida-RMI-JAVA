package calc.master.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import calc.master.exception.ConexaoRecusadaException;
import calc.master.util.Util;
import calc.rmi.stub.SlaveBasico;
import calc.rmi.stub.SlaveEspecial;
import calc.socket.model.Mensagem;
import calc.socket.model.TipoMensagem;

public class MasterServer implements Runnable {

	private int portaSocket;
	private int portaRMI;
	private boolean canRun = true;

	private MasterImple masterRMI;
	private ServerSocket serverSocket;
	private Socket socket;

	private Registry registry = null;

	private ExecutorService executorService;

	public MasterServer(int portaSocket, int portaRMI) {
		executorService = Executors.newCachedThreadPool();
		this.portaSocket = portaSocket;
		this.portaRMI = portaRMI;
	}

	@Override
	public void run() {
		iniciaServerRMI();
		iniciaServerSocket();
	}

	public void desligar() {
		canRun = false;
		try {
			registry.unbind("Master");
			masterRMI.shutdown();
			executorService.shutdown();
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	private void iniciaServerRMI() {
		try {
			Runtime.getRuntime().exec("rmid -J-Djava.security.policy=rmid.policy");
			registry = LocateRegistry.createRegistry(portaRMI);
			masterRMI = new MasterImple();
			registry.rebind("Master", masterRMI);
			System.out.println("Servidor Master RMI ONLINE na porta " + portaRMI);

		} catch (IOException e) {
			System.out.println("Erro ao iniciar o Servidor Master RMI na porta " + portaRMI);

		}

	}

	private void iniciaServerSocket() {

		try {
			serverSocket = new ServerSocket(portaSocket);
			System.out.println("Servidor Master Socket ONLINE na porta " + portaSocket);
			while (canRun) {
				System.out.println("Aguardando conexao...");
				if (!serverSocket.isClosed()) {
					socket = serverSocket.accept();
					System.out.println("Cliente Conectado do ip: " + socket.getInetAddress() );
					SockerMaster sockerMaster = new SockerMaster(socket);
					executorService.execute(sockerMaster);
				}
			}
		} catch (IOException e) {
			canRun = false;
			e.printStackTrace();
			System.out.println("Erro ao iniciar o Servidor Master Socket na porta " + portaSocket);
		}

	}

	private class SockerMaster extends AbstractSocket {
		public SockerMaster(Socket socket) throws IOException {
			super(socket);
		}

		@Override
		public void run() {
			try {
				synchronized (in) {

					Mensagem msg = null;
					while (socket.isConnected()) {
						msg = recebe();
						if (msg != null) {
							if (msg.getTipo().equals(TipoMensagem.TESTE)) {
								msg.setResposta("OK");
								msg.setTipo(TipoMensagem.TESTE);
								envia(msg);

							} else if (msg.getTipo().equals(TipoMensagem.SAIR)) {
								close();
								System.out.println("Cliente " + socket.getRemoteSocketAddress() + " Saiu!");

							} else if (msg.getTipo().equals(TipoMensagem.MENSAGEM)) {
								envia(processar(msg));
							}
						}
					}

				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				throw new ConexaoRecusadaException("Não foi possivel responder sua solicitação!");
			}
		}
	}

	private Mensagem processar(Mensagem msg) throws IOException {
		String calculo = msg.getMensagem();
		String operador = Util.getOperador(calculo);
		String[] numero = calculo.split(String.format("\\%s", Util.getOperador(calculo)));
		Double resultado = null;
		if (isBasico(operador)) {
			SlaveBasico basico = masterRMI.getBasicos().poll();
			Double a = Double.parseDouble(numero[0]);
			Double b = Double.parseDouble(numero[1]);
			switch (operador) {
			case "+":
				resultado = basico.getSoma(a, b);
				break;
			case "-":
				resultado = basico.getSubtracao(a, b);
				break;
			case "*":
				resultado = basico.getMultiplicacao(a, b);
				break;
			case "/":
				resultado = basico.getDivisao(a, b);
				break;
			}
			masterRMI.getBasicos().add(basico);
		} else {
			SlaveEspecial especial = masterRMI.getEspeciais().poll();
			Double a = Double.parseDouble(numero[0]);
			switch (operador) {
			case "√":
				resultado = especial.getRaiz(a);
				break;
			case "%":
				resultado = especial.getPorcentagem(a);
				break;
			}
			masterRMI.getEspeciais().add(especial);
		}

		String resposta = limpaResposta(resultado);
		msg.setResposta(resposta);
		return msg;
	}

	private boolean isBasico(String operador) {
		return operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/");
	}

	private String limpaResposta(Double r) {
		String resposta = String.valueOf(r);
		if (resposta.endsWith(".0")) {
			resposta = resposta.substring(0, resposta.length() - 2);
		}
		return resposta.replaceAll("\\.", ",");
	}

}
