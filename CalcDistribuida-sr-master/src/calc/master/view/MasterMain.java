package calc.master.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import calc.master.controller.MasterController;

public class MasterMain {

	private JFrame frame;
	private JTextField txtPortaSocket;
	private JTextField txtPortaRMI;
	private JTextArea console;
	private JButton btnConectar;
	
	private MasterController controller;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterMain window = new MasterMain();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MasterMain() {
		controller = new MasterController();
		
		initialize();
		
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnConectar.getText().equalsIgnoreCase("Iniciar Servidor")){
					int portaSocket = Integer.parseInt(txtPortaSocket.getText());
					int portaRMI = Integer.parseInt(txtPortaRMI.getText());
					controller.iniciarServidor(portaSocket, portaRMI);
					btnConectar.setText("Parar Servidor");
				}else{
					controller.paraServidor();
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
		frame.setBounds(100, 100, 339, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 314, 154);
		frame.getContentPane().add(scrollPane);
		
		console = new JTextArea();
		console.setEditable(false);
		scrollPane.setViewportView(console);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Configura\u00E7\u00F5es do Servidor Master", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 176, 314, 139);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPortaSocket = new JLabel("Porta Socket");
		lblPortaSocket.setBounds(10, 29, 185, 14);
		panel.add(lblPortaSocket);
		
		txtPortaSocket = new JTextField();
		lblPortaSocket.setLabelFor(txtPortaSocket);
		txtPortaSocket.setText("12345");
		txtPortaSocket.setBounds(10, 45, 185, 26);
		panel.add(txtPortaSocket);
		txtPortaSocket.setColumns(10);
		
		txtPortaRMI = new JTextField();
		txtPortaRMI.setText("1234");
		txtPortaRMI.setColumns(10);
		txtPortaRMI.setBounds(11, 102, 185, 26);
		panel.add(txtPortaRMI);
		
		JLabel lblPortaRmi = new JLabel("Porta RMI");
		lblPortaRmi.setLabelFor(txtPortaRMI);
		lblPortaRmi.setBounds(11, 86, 185, 14);
		panel.add(lblPortaRmi);
		
		btnConectar = new JButton("Iniciar Servidor");
		btnConectar.setBounds(190, 326, 133, 32);
		frame.getContentPane().add(btnConectar);
	}

	
}
