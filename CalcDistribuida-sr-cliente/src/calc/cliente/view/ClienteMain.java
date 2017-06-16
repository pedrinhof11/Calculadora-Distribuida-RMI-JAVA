package calc.cliente.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import calc.cliente.controller.ClienteController;
import calc.cliente.util.Util;


public class ClienteMain {

	private JFrame frame;
	private JTextField display;
    private JButton btn_7;
    private JButton btn_1;
    private JButton btn_2;
    private JButton btn_3;
    private JButton btn_4;
    private JButton btn_5;
    private JButton btn_6;
    private JButton btn_point;
    private JButton btn_8;
    private JButton btn_9;
    private JButton btn_adicao;
    private JButton btn_subtracao;
    private JButton btn_mult;
    private JButton btn_0;
    private JButton btn_div;
    private JButton btn_limpar;
    private JButton btn_porc;
    private JButton btn_raiz;
    private JButton btn_result;
    private JTextArea console;
    
    private JMenuBar menuBar;
    private JMenu mnArquivo;
    private JMenuItem mntmSair;
    private JMenu mnConfiguraoes;
    private JMenuItem mntmReconectar;

    private String teste;

    private ClienteController controller;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteMain window = new ClienteMain();
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
	public ClienteMain() {
		controller = new ClienteController();
		
		initialize();
		
        teste = controller.configuraConexao();
        if (teste != null) console.append("Teste de Conexão \t= " + teste);


        btn_0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (!"0".equals(display.getText())) {
            		 display.setText(display.getText() + "0");
        		}
            }
        });
        btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("1");
                } else {
                    display.setText(display.getText() + 1);
                }
            }
        });
        btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("2");
                } else {
                    display.setText(display.getText() + 2);
                }
            }
        });
        btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("3");
                } else {
                    display.setText(display.getText() + 3);
                }
            }
        });
        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("4");
                } else {
                    display.setText(display.getText() + 4);
                }
            }
        });
        btn_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("5");
                } else {
                    display.setText(display.getText() + 5);
                }
            }
        });
        btn_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("6");
                } else {
                    display.setText(display.getText() + 6);
                }
            }
        });
        btn_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("7");
                } else {
                    display.setText(display.getText() + 7);
                }
            }
        });
        btn_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("8");
                } else {
                    display.setText(display.getText() + 8);
                }
            }
        });
        btn_9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("0".equals(display.getText())) {
                    display.setText("9");
                } else {
                    display.setText(display.getText() + 9);
                }
            }
        });
        btn_limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("0");
            }
        });
        btn_point.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calculo = display.getText();
                if (Util.verificaOperador(calculo)) {
                    String[] cs = calculo.split(String.format("\\%s", Util.getOperador(calculo)));
                    if (calculo.endsWith(Util.getOperador(calculo))) {
                        display.setText(display.getText() + "0,");
                    } else if (cs[1] != null) {
                        if (!cs[1].contains(",")) {
                            display.setText(display.getText() + ",");
                        }
                    }
                } else {
                    if (!calculo.contains(",")) {
                        display.setText(display.getText() + ",");
                    }
                }
            }
        });
        btn_adicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calculo = display.getText();

                if (Util.verificaOperador(calculo)) {
                    if (calculo.endsWith(Util.getOperador(calculo))) {
                        calculo += calculo.substring(0, calculo.length() - 1);
                        String resposta = controller.calcular(calculo);
                        console.append("\n"+calculo + "\t= " + resposta);
                        display.setText(resposta);
                    }
                } else {
                    if (calculo.endsWith(",")) {
                        display.setText(calculo.substring(0, calculo.length() - 1) + "+");
                    } else {
                        display.setText(calculo + "+");
                    }
                }

            }
        });
        btn_subtracao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calculo = display.getText();

                if (Util.verificaOperador(calculo)) {
                    if (calculo.endsWith(Util.getOperador(calculo))) {
                        calculo += calculo.substring(0, calculo.length() - 1);
                        String resposta = controller.calcular(calculo);
                        console.append("\n"+calculo + "\t= " + resposta);
                        display.setText(resposta);
                    }
                } else {
                    if (calculo.endsWith(",")) {
                        display.setText(calculo.substring(0, calculo.length() - 1) + "-");
                    } else {
                        display.setText(calculo + "-");
                    }
                }
            }
        });
        btn_mult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calculo = display.getText();

                if (Util.verificaOperador(calculo)) {
                    if (calculo.endsWith(Util.getOperador(calculo))) {
                        calculo += calculo.substring(0, calculo.length() - 1);
                        String resposta = controller.calcular(calculo);
                        console.append("\n"+calculo + "\t= " + resposta);
                        display.setText(resposta);
                    }
                } else {
                    if (calculo.endsWith(",")) {
                        display.setText(calculo.substring(0, calculo.length() - 1) + "*");
                    } else {
                        display.setText(calculo + "*");
                    }
                }
            }
        });
        btn_div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calculo = display.getText();

                if (Util.verificaOperador(calculo)) {
                    if (calculo.endsWith(Util.getOperador(calculo))) {
                        calculo += calculo.substring(0, calculo.length() - 1);
                        String resposta = controller.calcular(calculo);
                        console.append("\n"+calculo + "\t= " + resposta);
                        display.setText(resposta);
                    }
                } else {
                    if (calculo.endsWith(",")) {
                        display.setText(calculo.substring(0, calculo.length() - 1) + "/");
                    } else {
                        display.setText(calculo + "/");
                    }
                }
            }
        });
        btn_result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calculo = display.getText();
                if (calculo != "0") {
                    if (Util.verificaOperador(calculo)) {
                        if (calculo.endsWith(",")) {
                            calculo = calculo.substring(0, calculo.length() - 1);
                        }
                        if (calculo.endsWith(Util.getOperador(calculo))) {
                            calculo += calculo.substring(0, calculo.length() - 1);
                        }
                        String resposta = controller.calcular(calculo);
                        console.append("\n"+calculo +"\t= " + resposta);
                        display.setText(resposta);
                    }
                }
            }
        });
        btn_raiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calculo = display.getText();
                if (calculo != "0") {
                    if(!Util.verificaOperador(calculo)){
                        if (calculo.endsWith(",")) {
                            calculo = calculo.substring(0, calculo.length() - 1);
                        }

                        String resposta = controller.calcular(calculo + "√");
                        console.append("\n"+ "√" + calculo +"\t= " + resposta);
                        display.setText(resposta);
                    }
                }
            }
        });

        btn_porc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String calculo = display.getText();
                if (calculo != "0") {
                    if(!Util.verificaOperador(calculo)){
                        if (calculo.endsWith(",")) {
                            calculo = calculo.substring(0, calculo.length() - 1);
                        }
                        String resposta = controller.calcular(calculo + "%");
                        console.append("\n"+ calculo +"% \t= " + resposta);
                        display.setText(resposta);
                    }
                }
            }
        });

        mntmReconectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teste = controller.configuraConexao();
                if (teste.equalsIgnoreCase("Ok"))
                    console.append("\nTeste de Conexão \t= " + teste);
                else
                    console.append("\nTeste de Conexão \t= " + "Falhou");
            }
        });
        mntmSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sair();
            }
        });

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 296, 394);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                controller.sair();
            }

        });
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		
		mnConfiguraoes = new JMenu("Configurações");
		menuBar.add(mnConfiguraoes);
		
		mntmReconectar = new JMenuItem("Reconectar");
		mnConfiguraoes.add(mntmReconectar);
		frame.getContentPane().setLayout(null);
		
		
		console = new JTextArea();
		
		console.setAutoscrolls(true);
		console.setEditable(false);
		JScrollPane scroll = new JScrollPane(console);
		scroll.setBounds(10, 11, 260, 81);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		frame.getContentPane().add(scroll);
		
		display = new JTextField();
		display.setBackground(Color.WHITE);
		display.setEditable(false);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setText("0");
		display.setFont(new Font("Tahoma", Font.BOLD, 18));
		display.setBounds(10, 103, 260, 35);
		frame.getContentPane().add(display);
		display.setColumns(10);
		
		btn_7 = new JButton("7");
		btn_7.setBounds(10, 149, 44, 35);
		frame.getContentPane().add(btn_7);
		
		btn_8 = new JButton("8");
		btn_8.setBounds(64, 149, 44, 35);
		frame.getContentPane().add(btn_8);
		
		btn_9 = new JButton("9");
		btn_9.setBounds(118, 149, 44, 35);
		frame.getContentPane().add(btn_9);
		
		btn_adicao = new JButton("+");
		btn_adicao.setBounds(172, 149, 44, 35);
		frame.getContentPane().add(btn_adicao);
		
		btn_limpar = new JButton("C");
		btn_limpar.setBounds(10, 287, 44, 35);
		frame.getContentPane().add(btn_limpar);
		
		btn_4 = new JButton("4");
		btn_4.setBounds(10, 195, 44, 35);
		frame.getContentPane().add(btn_4);
		
		btn_5 = new JButton("5");
		btn_5.setBounds(64, 195, 44, 35);
		frame.getContentPane().add(btn_5);
		
		btn_6 = new JButton("6");
		btn_6.setBounds(118, 195, 44, 35);
		frame.getContentPane().add(btn_6);
		
		btn_subtracao = new JButton("-");
		btn_subtracao.setBounds(172, 195, 44, 35);
		frame.getContentPane().add(btn_subtracao);
		
		btn_porc = new JButton("%");
		btn_porc.setBounds(226, 149, 44, 35);
		frame.getContentPane().add(btn_porc);
		
		btn_1 = new JButton("1");
		btn_1.setBounds(10, 241, 44, 35);
		frame.getContentPane().add(btn_1);
		
		btn_2 = new JButton("2");
		btn_2.setBounds(64, 241, 44, 35);
		frame.getContentPane().add(btn_2);
		
		btn_3 = new JButton("3");
		btn_3.setBounds(118, 241, 44, 35);
		frame.getContentPane().add(btn_3);
		
		btn_mult = new JButton("*");
		btn_mult.setBounds(172, 241, 44, 35);
		frame.getContentPane().add(btn_mult);
		
		btn_raiz = new JButton("√");
		btn_raiz.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btn_raiz.setBounds(226, 195, 44, 35);
		frame.getContentPane().add(btn_raiz);
		
		btn_0 = new JButton("0");
		btn_0.setBounds(64, 287, 44, 35);
		frame.getContentPane().add(btn_0);
		
		btn_point = new JButton(",");
		btn_point.setBounds(118, 287, 44, 35);
		frame.getContentPane().add(btn_point);
		
		btn_div = new JButton("/");
		btn_div.setBounds(172, 287, 44, 35);
		frame.getContentPane().add(btn_div);
		
		btn_result = new JButton("=");
		btn_result.setBounds(226, 241, 44, 81);
		frame.getContentPane().add(btn_result);
	}
}
