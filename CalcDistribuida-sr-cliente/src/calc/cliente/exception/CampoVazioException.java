package calc.cliente.exception;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CampoVazioException extends RuntimeException {
	
	private static final long serialVersionUID = 9159182845390953457L;

	public CampoVazioException(String s) {
        super(s);

        JLabel lblMessage = new JLabel(s);
        Object[] texts = { lblMessage };
        JOptionPane.showMessageDialog(null, texts);
    }
}