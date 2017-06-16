package calc.master.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import calc.socket.model.Mensagem;

public abstract class AbstractSocket implements Runnable {

	protected Socket socket;
	protected ObjectInputStream in;
	protected ObjectOutputStream out;

	public AbstractSocket(Socket socket) throws IOException {
		this.socket = socket;
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
	}

	public Mensagem recebe() throws IOException, ClassNotFoundException {
		Mensagem msg = null;
		if (!socket.isClosed()) {
			msg = (Mensagem) in.readObject();
		}
		return msg;
	}

	public void envia(Mensagem msg) throws IOException {
		if (!socket.isClosed()) {
			out.writeObject(msg);
		}
	}

	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}



}
