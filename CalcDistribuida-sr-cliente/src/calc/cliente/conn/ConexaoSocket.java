package calc.cliente.conn;

import java.io.IOException;

public class ConexaoSocket extends AbstractConexao {

    public ConexaoSocket(String host, int porta) throws IOException {
        super(host, porta);

    }

}