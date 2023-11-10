package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.DBConnection;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connect;

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
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 350);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("PACIENTE");
		menuBar.add(mnNewMenu);

		JMenuItem showPatient = new JMenuItem("Ver");
		showPatient.setEnabled(false);
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
				RegisterPatient createWindow = new RegisterPatient();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnNewMenu.add(registerPatient);

		JMenu mnMostrar = new JMenu("MEDICO");
		menuBar.add(mnMostrar);

		JMenuItem showDoctor = new JMenuItem("Ver");
		showDoctor.setEnabled(false);
		mnMostrar.add(showDoctor);

		JMenuItem registerDoctor = new JMenuItem("Registro");
		registerDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterMedical createWindow = new RegisterMedical();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnMostrar.add(registerDoctor);

		JMenu mnInvoice = new JMenu("FACTURA");
		menuBar.add(mnInvoice);

		JMenuItem showInvoice = new JMenuItem("Ver");
		showInvoice.setEnabled(false);
		mnInvoice.add(showInvoice);

		JMenuItem registerInvoice = new JMenuItem("Registro");
		registerInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterInvoice createWindow = new RegisterInvoice();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		mnInvoice.add(registerInvoice);
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
		lblNewLabel_1.setBounds(20, 64, 332, 58);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("CLINICA DEL PILAR");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1_1_1.setBounds(20, 114, 332, 58);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel alertlbl = new JLabel("");
		alertlbl.setForeground(new Color(255, 0, 0));
		alertlbl.setFont(new Font("Arial", Font.PLAIN, 11));
		alertlbl.setHorizontalAlignment(SwingConstants.CENTER);
		alertlbl.setBounds(20, 217, 326, 35);
		contentPane.add(alertlbl);

		JButton reigsterApoinmentBtn = new JButton("REGISTRO DE CITA");
		reigsterApoinmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterAppoinment createWindow = new RegisterAppoinment();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		reigsterApoinmentBtn.setFont(new Font("Arial", Font.BOLD, 14));
		reigsterApoinmentBtn.setBounds(20, 183, 332, 23);
		contentPane.add(reigsterApoinmentBtn);

		boolean patientsEmpty = checkIfTableIsEmpty("Patient");
		boolean doctorsEmpty = checkIfTableIsEmpty("Doctor");

		reigsterApoinmentBtn.setEnabled(!(patientsEmpty || doctorsEmpty));

		alertlbl.setText(
				(patientsEmpty || doctorsEmpty) ? "Antes de registrar una cita, registrar un paciente y médico." : null);

		mnInvoice.setEnabled(!patientsEmpty);
	}

	private boolean checkIfTableIsEmpty(String tableName) {
		boolean isEmpty = false;
		try {
			String query = "SELECT COUNT(*) as count FROM " + tableName;
			PreparedStatement st = connect.prepareStatement(query);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int count = rs.getInt("count");
				isEmpty = count == 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isEmpty;
	}
}
