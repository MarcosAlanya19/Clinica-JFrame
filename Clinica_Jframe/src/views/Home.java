package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 350);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("PACIENTE");
		menuBar.add(mnNewMenu);

		JMenuItem showPatient = new JMenuItem("Ver");
		showPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterSpeciality createWindow = new RegisterSpeciality();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnNewMenu.add(showPatient);

		JMenuItem registerPatient = new JMenuItem("Registro");
		registerPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterQuote createWindow = new RegisterQuote();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnNewMenu.add(registerPatient);

		JMenu mnMostrar = new JMenu("MEDICO");
		menuBar.add(mnMostrar);

		JMenuItem showDoctor = new JMenuItem("Ver");
		mnMostrar.add(showDoctor);

		JMenuItem registerDoctor = new JMenuItem("Registro");
		registerDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegistroMedico createWindow = new RegistroMedico();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnMostrar.add(registerDoctor);
		
		JMenu mnNewMenu_1 = new JMenu("FACTURA");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem showInvoice = new JMenuItem("Ver");
		mnNewMenu_1.add(showInvoice);
		
		JMenuItem registerInvoice = new JMenuItem("Registro");
		mnNewMenu_1.add(registerInvoice);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setForeground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/img/clinicaSMP.png")));
		lblNewLabel.setBounds(362, 21, 250, 239);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Bienvenido");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(20, 39, 327, 58);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("CLINICA DEL PILAR");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1_1_1.setBounds(20, 88, 329, 58);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton reigsterQuoteBtn = new JButton("REGISTRO DE CITA");
		reigsterQuoteBtn.setFont(new Font("Arial", Font.BOLD, 14));
		reigsterQuoteBtn.setBounds(20, 183, 329, 23);
		contentPane.add(reigsterQuoteBtn);
		
		JLabel lblNewLabel_2 = new JLabel("Antes de registrar una cita, registrar un paciente y medico.\r\n");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(20, 217, 326, 35);
		contentPane.add(lblNewLabel_2);
	}
}
