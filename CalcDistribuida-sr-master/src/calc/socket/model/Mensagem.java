package calc.socket.model;

import java.io.Serializable;

public class Mensagem implements Serializable {

	private static final long serialVersionUID = -7525455303809611858L;

	private TipoMensagem tipo;
	private String mensagem;
	private String resposta;

	public TipoMensagem getTipo() {
		return tipo;
	}

	public void setTipo(TipoMensagem tipo) {
		this.tipo = tipo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

}
