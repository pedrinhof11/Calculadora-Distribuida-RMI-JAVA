package calc.cliente.conn;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import calc.socket.model.Mensagem;
import calc.socket.model.TipoMensagem;

public abstract class AbstractConexao implements Conexao {

    private Socket conn;

    private String host;
    private int porta;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public AbstractConexao(String host, int porta){
        this.host = host;
        this.porta = porta;
    }

    @Override
    public void iniciaConn() throws IOException {
        conn = new Socket(host, porta);
        conn.setReuseAddress(true);
        conn.setTcpNoDelay(true);
        out = new ObjectOutputStream(conn.getOutputStream());
        in = new ObjectInputStream(conn.getInputStream());
    }

    @Override
    public void closeConn() throws IOException {

        if(conn!=null && conn.isConnected()) {
            if(conn.isClosed()){
                iniciaConn();
            }
            msgSair();
            in.close();
            out.close();
            conn.close();
        }
    }
    
    private void msgSair() throws IOException{
    	Mensagem msg = new Mensagem();
    	msg.setTipo(TipoMensagem.SAIR);
    	msg.setMensagem("Sair");
    	enviar(msg);
    }

    @Override
    public void enviar(Mensagem msg) throws IOException {
        if(!conn.isConnected()){
            iniciaConn();

        }
        out.writeObject(msg);

    }

    @Override
    public Mensagem receber() throws IOException, ClassNotFoundException{
        if(!conn.isConnected()){
            iniciaConn();
        }
        return (Mensagem) in.readObject();
    }
    
    @Override
    public Mensagem testeConexao() throws IOException, ClassNotFoundException {
        Mensagem msg = new Mensagem();
        msg.setTipo(TipoMensagem.TESTE);
    	msg.setMensagem("Teste");
    	if(!conn.isConnected()){
            iniciaConn();
        }
        enviar(msg);
        return receber();
    }

}