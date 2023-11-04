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

		JMenu mnNewMenu = new JMenu("REGISTROS");
		menuBar.add(mnNewMenu);

		JMenuItem registerPatient = new JMenuItem("PACIENTE");
		registerPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterPatient createWindow = new RegisterPatient();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnNewMenu.add(registerPatient);

		JMenuItem registerSpecialist = new JMenuItem("ESPECIALISTA");
		registerSpecialist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterSpecialist createWindow = new RegisterSpecialist();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnNewMenu.add(registerSpecialist);

		JMenuItem registerSpeciality = new JMenuItem("ESPECIALIDAD");
		registerSpeciality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterSpeciality createWindow = new RegisterSpeciality();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnNewMenu.add(registerSpeciality);

		JMenuItem registerQuote = new JMenuItem("CITA");
		registerQuote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterQuote createWindow = new RegisterQuote();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnNewMenu.add(registerQuote);

		JMenu mnMostrar = new JMenu("MOSTRAR");
		menuBar.add(mnMostrar);

		JMenuItem showPatient = new JMenuItem("PACIENTE");
		mnMostrar.add(showPatient);

		JMenuItem showSpecialist = new JMenuItem("ESPECIALISTA");
		mnMostrar.add(showSpecialist);

		JMenuItem showSpeciality = new JMenuItem("ESPECIALIDAD");
		mnMostrar.add(showSpeciality);

		JMenuItem showQuote = new JMenuItem("CITA");
		mnMostrar.add(showQuote);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setForeground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/img/clinicaSMP.png")));
		lblNewLabel.setBounds(357, 21, 250, 239);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Bienvenido");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(21, 57, 338, 58);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("al aplicativo \r\nde la");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(21, 110, 338, 52);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("CLINICA DEL PILAR");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1_1_1.setBounds(21, 162, 338, 58);
		contentPane.add(lblNewLabel_1_1_1);
	}
}
