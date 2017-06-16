package calc.slave.server;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import calc.rmi.stub.Master;
import calc.rmi.stub.SlaveEspecial;

public class SlaveEspecialImple extends UnicastRemoteObject implements SlaveEspecial{

	private static final long serialVersionUID = 7741187623911762209L;

	private ExecutorService executorService;
	
	public SlaveEspecialImple() throws RemoteException {
		super();
		executorService = Executors.newFixedThreadPool(1);
	}

	public void VerificaMasterOnline(Master master) {
		executorService.execute(new calc.slave.util.VerificaMasterOnline(master));
	}	

	@Override
	public boolean isOnline() throws RemoteException {
		return true;
	}

	@Override
	public Double getPorcentagem(Double a) throws RemoteException {
		Double resposta;
		resposta = new BigDecimal(a).divide(new BigDecimal(100)).doubleValue();
		System.out.println("PORCENTAGEM: " + a + "%  = " + resposta);
		return resposta;
	}
	
	@Override
	public Double getPotenciacao(int a, int b) throws RemoteException {
		Double resposta;
		resposta = new BigDecimal(a).pow(b).doubleValue();
		System.out.println("POTENCIAÇÃO: " + a + " elevado à " + b + " = " + resposta);
		return resposta;
	}


	@Override
	public Double getRaiz(Double a) throws RemoteException {
		Double resposta;
		resposta = new BigDecimal(Math.sqrt(a)).doubleValue();
		System.out.println("RAIZ QUADRADA: Raiz quadrada de " + a + " = " + resposta);
		return resposta;
	}





}
