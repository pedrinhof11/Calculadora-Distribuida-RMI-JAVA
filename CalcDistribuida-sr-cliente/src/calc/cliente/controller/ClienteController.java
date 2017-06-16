package calc.cliente.controller;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import calc.cliente.conn.Conexao;
import calc.cliente.conn.ConexaoSocket;
import calc.cliente.exception.CampoVazioException;
import calc.cliente.exception.ConexaoRecusadaException;
import calc.socket.model.Mensagem;
import calc.socket.model.TipoMensagem;

public class ClienteController {

	private JTextField txtHost;
	private JTextField txtPorta;

	private Conexao conexao;

	public String configuraConexao() {
		dialogConexao();
		try {
			if (conexao != null) {
				conexao.closeConn();
				conexao = null;
			}
			conexao = new ConexaoSocket(getHost(), getPorta());
			conexao.iniciaConn();
			Mensagem msg = conexao.testeConexao();
			if (msg.getResposta().equalsIgnoreCase("OK")) {
				dialogTesteOK();
			}
			return msg.getResposta();
		} catch (IOException | ClassNotFoundException e) {
			if (txtHost.getText().isEmpty() || txtPorta.getText().isEmpty()) {
				throw new CampoVazioException("Por favor, digite o host e a porta para conectar!");
			} else {
				throw new ConexaoRecusadaException("Não foi possivel estabelecer conexão. Tente novamente!");
			}
		} 
	}

	private void dialogConexao() {
		JLabel lblMessage = new JLabel("Digite o Host e a Porta do Servidor Master!");
		txtHost = new JTextField("127.0.0.1");
		txtPorta = new JTextField("12345");
		Object[] texts = { lblMessage, txtHost, txtPorta };
		JOptionPane.showMessageDialog(null, texts);
	}

	private void dialogTesteOK() {
		JLabel lblMessage = new JLabel("Conectado com sucesso!");
		Object[] texts = { lblMessage };
		JOptionPane.showMessageDialog(null, texts);
	}

	public void sair() {
		int answer = JOptionPane.showConfirmDialog(null, "Quer mesmo sair?", "Fechando o Conexão com segurança...",
				JOptionPane.YES_NO_OPTION);

		if (answer == JOptionPane.YES_OPTION) {
			try {
				if (conexao != null)
					conexao.closeConn();
			} catch (IOException e) {

			}
			System.exit(0);
		}
		return;
	}

	public String calcular(String calculo) {
		String resultado = null;
		try {
			while (resultado == null) {
				if (!calculo.isEmpty()) {
					Mensagem msg = new Mensagem();
					msg.setTipo(TipoMensagem.MENSAGEM);
					msg.setMensagem(calculo.replaceAll(",", "."));
					conexao.enviar(msg);
				}
				resultado = conexao.receber().getResposta();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			resultado = "0";
			throw new ConexaoRecusadaException(
					"Não foi possivel responder sua solicitação! \r\n" + " Tente novamente!");
		}
		return resultado;
	}

	public String getHost() {
		return txtHost.getText();
	}

	public int getPorta() {
		return Integer.parseInt(txtPorta.getText());
	}	
	
}
