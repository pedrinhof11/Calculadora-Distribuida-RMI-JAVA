package calc.slave.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import calc.slave.controller.SlaveEspecialController;

public class SlaveEspecialMain {

	private JFrame frame;
    private JTextField txtPorta;
    private JButton btnConectar;
    private JTextArea console;
    
    private SlaveEspecialController controller;
    private JTextField txtHost;
    private JLabel lblhost;
    private JLabel lblPorta;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlaveEspecialMain window = new SlaveEspecialMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SlaveEspecialMain() {
		controller = new SlaveEspecialController();
		initialize();
		btnConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (controller.getServer() == null) {
                    String r = controller.iniciarServidor(txtHost.getText(), txtPorta.getText());
                    if(r != null) console.append("\r\nIniciando Servidor \t= " + r);
                    btnConectar.setText("Desligar");
                } else {
                    String r = controller.desligarServidor();
                    if(r != null) console.append("\r\nDesligando Servidor \t= " + r);
                    btnConectar.setText("Iniciar Servidor");
                }
            }
        });
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 263, 286);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
	                controller.sair();
	            }
	        });
		frame.getContentPane().setLayout(null);
		
		console = new JTextArea();
		console.setBounds(10, 11, 227, 81);
		frame.getContentPane().add(console);
		
		btnConectar = new JButton("Iniciar Servidor");
		btnConectar.setBounds(77, 211, 160, 26);
		frame.getContentPane().add(btnConectar);
		
		txtPorta = new JTextField();
		txtPorta.setBounds(10, 180, 227, 23);
		frame.getContentPane().add(txtPorta);
		txtPorta.setColumns(10);
		
		lblPorta = new JLabel("Porta do Master:");
		lblPorta.setBounds(10, 158, 146, 14);
		frame.getContentPane().add(lblPorta);
		
		txtHost = new JTextField();
		txtHost.setColumns(10);
		txtHost.setBounds(10, 124, 227, 23);
		frame.getContentPane().add(txtHost);
		
		lblhost = new JLabel("Host do Master:");
		lblhost.setBounds(10, 102, 160, 14);
		frame.getContentPane().add(lblhost);
	}
}
