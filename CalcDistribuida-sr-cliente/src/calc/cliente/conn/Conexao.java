package calc.cliente.conn;

import java.io.IOException;

import calc.socket.model.Mensagem;

/**
 * Created by Pedro Felipe on 18/04/2017.
 */
public interface Conexao{

    public void iniciaConn() throws IOException;

    public void closeConn() throws IOException;

    public void enviar(Mensagem mensagem) throws IOException;

    public Mensagem receber() throws IOException, ClassNotFoundException;

    public Mensagem testeConexao() throws IOException, ClassNotFoundException;

}