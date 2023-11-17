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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import contructor.Doctor;
import contructor.Patient;
import model.DBConnection;

public class RegisterAppoinment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connect;
	JComboBox<Object> patientSelect;
	JComboBox<Object> doctorSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAppoinment frame = new RegisterAppoinment();
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
	public RegisterAppoinment() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterAppoinment.class.getResource("/img/logo.png")));
		connect = DBConnection.getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("REGISTRO DE CITA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(168, 11, 307, 57);
		contentPane.add(lblNewLabel);

		patientSelect = new JComboBox<Object>();
		showPatientSelect();
		patientSelect.setBounds(148, 235, 166, 22);
		contentPane.add(patientSelect);

		doctorSelect = new JComboBox<Object>();
		showDoctorSelect();
		doctorSelect.setFont(new Font("Arial", Font.BOLD, 14));
		doctorSelect.setBounds(148, 282, 166, 22);
		contentPane.add(doctorSelect);

		JDateChooser dateField = new JDateChooser();
		dateField.setBounds(148, 93, 166, 20);
		dateField.setDate(new java.util.Date());
		dateField.setMinSelectableDate(new java.util.Date());
		contentPane.add(dateField);

		JTextArea descriptionField = new JTextArea();
		descriptionField.setBounds(148, 139, 166, 67);
		contentPane.add(descriptionField);

		JLabel errDescription = new JLabel("");
		errDescription.setForeground(new Color(255, 0, 0));
		errDescription.setFont(new Font("Arial", Font.PLAIN, 9));
		errDescription.setBounds(148, 210, 166, 14);
		contentPane.add(errDescription);

		JLabel errPatient = new JLabel("");
		errPatient.setForeground(Color.RED);
		errPatient.setFont(new Font("Arial", Font.PLAIN, 9));
		errPatient.setBounds(148, 257, 166, 14);
		contentPane.add(errPatient);

		JLabel errDoctor = new JLabel("");
		errDoctor.setForeground(Color.RED);
		errDoctor.setFont(new Font("Arial", Font.PLAIN, 9));
		errDoctor.setBounds(148, 307, 166, 14);
		contentPane.add(errDoctor);

		JLabel errDate = new JLabel("");
		errDate.setForeground(Color.RED);
		errDate.setFont(new Font("Arial", Font.PLAIN, 9));
		errDate.setBounds(148, 114, 166, 14);
		contentPane.add(errDate);

		JButton registerAppointmentBtn = new JButton("REGISTRO");
		registerAppointmentBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = dateField.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = sdf.format(selectedDate);

				String description = descriptionField.getText();

				if (description.isEmpty()) {
					errDescription.setText("Campo obligatorio");
					return;
				} else {
					errDescription.setText(null);
				}

				Patient selectedPatient = (Patient) patientSelect.getSelectedItem();
				int patientId = selectedPatient.getId();

				Doctor selectedDoctor = (Doctor) doctorSelect.getSelectedItem();
				int doctorId = selectedDoctor.getId();

				try {
					String query = "INSERT INTO Appointment (date, description, status, Doctor_id, Patient_id) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, formattedDate);
					st.setString(2, description);
					st.setString(3, "Programado");
					st.setInt(4, doctorId);
					st.setInt(5, patientId);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Cita creada correctamente");
					dateField.setDate(null);
					descriptionField.setText(null);
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}
			}
		});
		registerAppointmentBtn.setBounds(108, 330, 113, 23);
		contentPane.add(registerAppointmentBtn);

		JLabel dateLbl = new JLabel("Fecha:");
		dateLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		dateLbl.setBounds(36, 93, 102, 14);
		contentPane.add(dateLbl);

		JLabel descriptionLbl = new JLabel("Descripción:");
		descriptionLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		descriptionLbl.setBounds(36, 143, 102, 14);
		contentPane.add(descriptionLbl);

		JLabel patientLbl = new JLabel("Paciente:");
		patientLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		patientLbl.setBounds(36, 238, 102, 14);
		contentPane.add(patientLbl);

		JLabel doctorLbl = new JLabel("Doctor:");
		doctorLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		doctorLbl.setBounds(36, 286, 102, 14);
		contentPane.add(doctorLbl);

		JButton btnInicio = new JButton("INICIO");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		btnInicio.setFont(new Font("Arial", Font.BOLD, 14));
		btnInicio.setBounds(108, 364, 113, 23);
		contentPane.add(btnInicio);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(RegisterAppoinment.class.getResource("/img/appoinment.png")));
		lblNewLabel_1.setBounds(337, 93, 287, 270);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(36, 64, 278, 14);
		contentPane.add(lblNewLabel_2);
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

	private void showDoctorSelect() {
		try {
			String query = "SELECT id, name FROM Doctor";
			PreparedStatement st = connect.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			doctorSelect.removeAllItems();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = String.format("Dr. %s", rs.getString("name"));
				Doctor doctor = new Doctor(id, name);
				doctorSelect.addItem(doctor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
