package calc.master.exception;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConexaoRecusadaException extends RuntimeException {
	private static final long serialVersionUID = -3307189356179283383L;

	public ConexaoRecusadaException(String s) {
       super(s);

       JLabel lblMessage = new JLabel(s);
       Object[] texts = { lblMessage };
       JOptionPane.showMessageDialog(null, texts);
   }
}
