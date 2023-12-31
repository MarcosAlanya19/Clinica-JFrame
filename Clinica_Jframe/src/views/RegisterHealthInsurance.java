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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import contructor.Patient;
import model.DBConnection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterHealthInsurance extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField policyField;
	private JTextField detailsField;
	private JButton backBtn;
	private JLabel lblNewLabel_4;
	private Connection connect;
	private JComboBox<Patient> patientSelect;
	private JLabel lblNewLabel_5;
	private JLabel errorPoliceNumber;
	private JLabel errorPatient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterHealthInsurance frame = new RegisterHealthInsurance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterHealthInsurance() {
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterHealthInsurance.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("SEGURO MEDICO");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(165, 23, 334, 36);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Número poliza");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 112, 109, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Compañia de seguros:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(38, 158, 152, 17);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Detalle de compañia:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(37, 205, 134, 13);
		contentPane.add(lblNewLabel_3);

		policyField = new JTextField();
		policyField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					int key = e.getKeyChar();

					boolean numeros = key >= 48 && key <= 57;

					if (!numeros) {
						e.consume();
					}

					if (policyField.getText().trim().length() == 9) {
						e.consume();
					}
				} catch (Exception b) {
				}
			}
		});
		policyField.setBounds(200, 112, 134, 19);
		contentPane.add(policyField);
		policyField.setColumns(10);

		detailsField = new JTextField();
		detailsField.setBounds(200, 203, 134, 19);
		contentPane.add(detailsField);
		detailsField.setColumns(10);

		JComboBox<Object> insuranceCompañySelect = new JComboBox<Object>();
		insuranceCompañySelect.setFont(new Font("Arial", Font.PLAIN, 14));
		insuranceCompañySelect.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "SANITAS", "MAPFRE", "RIMAC", "PACÍFICO", "ONCOSALUD", "LA POSITIVA" }));
		insuranceCompañySelect.setBounds(200, 157, 134, 21);
		contentPane.add(insuranceCompañySelect);

		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient selectedPatient = (Patient) patientSelect.getSelectedItem();
				int patientId = selectedPatient.getId();

				String policy = policyField.getText();
				String detail = detailsField.getText();
				String company = (String) insuranceCompañySelect.getSelectedItem();

				boolean hasError = false;

				if (policy.isEmpty()) {
					errorPoliceNumber.setText("Campo obligatorio");
					hasError = true;
				} else {
					errorPoliceNumber.setText(null);
				}

				if (detail.isEmpty()) {
					errorPatient.setText("Campo obligatorio");
					hasError = true;
				} else {
					errorPatient.setText(null);
				}

				if (hasError) {
					return;
				}

				try {
					String query = "INSERT INTO healthinsurance (policyNumber, insuranceCompany, coverageDetails, Patient_id) VALUES (?, ?, ?, ?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, policy);
					st.setString(2, company);
					st.setString(3, detail);
					st.setLong(4, patientId);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Seguro registrada correctamente");

				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}
			}
		});
		registerBtn.setBounds(128, 326, 125, 21);
		contentPane.add(registerBtn);

		backBtn = new JButton("INICIO");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		backBtn.setFont(new Font("Arial", Font.BOLD, 14));
		backBtn.setBounds(128, 364, 125, 21);
		contentPane.add(backBtn);

		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(RegisterHealthInsurance.class.getResource("/img/healthlnsurance.png")));
		lblNewLabel_4.setBounds(344, 91, 292, 256);
		contentPane.add(lblNewLabel_4);

		patientSelect = new JComboBox<Patient>();
		showPatientSelect();
		patientSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		patientSelect.setBounds(200, 247, 134, 21);
		contentPane.add(patientSelect);

		lblNewLabel_5 = new JLabel("Paciente:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(37, 251, 76, 13);
		contentPane.add(lblNewLabel_5);

		errorPoliceNumber = new JLabel("");
		errorPoliceNumber.setForeground(new Color(255, 0, 0));
		errorPoliceNumber.setFont(new Font("Arial", Font.PLAIN, 9));
		errorPoliceNumber.setBounds(200, 130, 98, 13);
		contentPane.add(errorPoliceNumber);

		errorPatient = new JLabel("");
		errorPatient.setForeground(Color.RED);
		errorPatient.setFont(new Font("Arial", Font.PLAIN, 9));
		errorPatient.setBounds(200, 221, 98, 13);
		contentPane.add(errorPatient);
	}

	private void showPatientSelect() {
		try {
			String query = "SELECT id, name FROM Patient";
			PreparedStatement st = connect.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			patientSelect.removeAllItems();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Patient patient = new Patient(id, name);
				patientSelect.addItem(patient);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}