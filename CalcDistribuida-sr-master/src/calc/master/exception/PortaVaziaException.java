package calc.master.exception;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PortaVaziaException extends RuntimeException {

	private static final long serialVersionUID = 3294988227831616298L;

	public PortaVaziaException(String s) {
        super(s);

        JLabel lblMessage = new JLabel(s);
        Object[] texts = { lblMessage };
        JOptionPane.showMessageDialog(null, texts);
    }
}