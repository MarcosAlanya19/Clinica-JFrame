package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import model.DBConnection;

public class RegisterPatient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dniField;
	private JTextField addressField;
	private JTextField phoneField;
	private Connection connect;
	private JTextField ageField;
	private int idHistoryMedical = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPatient frame = new RegisterPatient();
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
	public RegisterPatient() {
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterPatient.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(322, 93, 114, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("REGISTRO PACIENTE");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(179, 30, 362, 27);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("DNI:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(322, 139, 45, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Fecha nacimiento:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(322, 182, 142, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Género:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(322, 226, 85, 13);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Dirección:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(322, 269, 114, 13);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Celular:");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(322, 309, 85, 13);
		contentPane.add(lblNewLabel_6);

		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					int key = e.getKeyChar();

					boolean mayusculas = key >= 65 && key <= 90;
					boolean minusculas = key >= 97 && key <= 122;
					boolean espacio = key == 32;

					if (!(minusculas || mayusculas || espacio)) {
						e.consume();
					}
				} catch (Exception c) {
				}
			}
		});
		nameField.setBounds(464, 91, 162, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);

		dniField = new JTextField();
		dniField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					int key = e.getKeyChar();

					boolean numeros = key >= 48 && key <= 57;

					if (!numeros) {
						e.consume();
					}

					if (dniField.getText().trim().length() == 8) {
						e.consume();
					}
				} catch (Exception b) {
				}
			}
		});
		dniField.setBounds(464, 137, 162, 19);
		contentPane.add(dniField);
		dniField.setColumns(10);

		JComboBox<String> genderSelect = new JComboBox<String>();
		genderSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		genderSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Masculino", "Femenino" }));
		genderSelect.setToolTipText("");
		genderSelect.setBounds(464, 222, 162, 22);
		contentPane.add(genderSelect);

		addressField = new JTextField();
		addressField.setBounds(464, 267, 162, 19);
		contentPane.add(addressField);
		addressField.setColumns(10);

		phoneField = new JTextField();
		phoneField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					int key = e.getKeyChar();

					boolean numeros = key >= 48 && key <= 57;

					if (!numeros) {
						e.consume();
					}

					if (phoneField.getText().trim().length() == 9) {
						e.consume();
					}
				} catch (Exception b) {
				}
			}
		});
		phoneField.setBounds(464, 307, 162, 19);
		contentPane.add(phoneField);
		phoneField.setColumns(10);

		JButton startBtn = new JButton("INICIO");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		startBtn.setFont(new Font("Arial", Font.BOLD, 14));
		startBtn.setBounds(91, 361, 114, 21);
		contentPane.add(startBtn);

		ageField = new JTextField();
		ageField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					int key = e.getKeyChar();

					boolean numeros = key >= 48 && key <= 57;

					if (!numeros) {
						e.consume();
					}

					if (ageField.getText().trim().length() == 3) {
						e.consume();
					}
				} catch (Exception b) {
				}
			}
		});
		ageField.setBounds(128, 137, 162, 19);
		contentPane.add(ageField);
		ageField.setColumns(10);

		JComboBox<String> bloodTypeSelect = new JComboBox<String>();
		bloodTypeSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "A", "B", "AB", "O" }));
		bloodTypeSelect.setToolTipText("");
		bloodTypeSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		bloodTypeSelect.setBounds(128, 93, 162, 22);
		contentPane.add(bloodTypeSelect);

		JLabel bloodTypeLabel = new JLabel("Tipo de sangre:");
		bloodTypeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		bloodTypeLabel.setBounds(10, 94, 129, 18);
		contentPane.add(bloodTypeLabel);

		JLabel lblNewLabel_7 = new JLabel("Edad:");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(10, 132, 45, 27);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Descripción:");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(10, 182, 90, 13);
		contentPane.add(lblNewLabel_8);

		JDateChooser birthdayDate = new JDateChooser();
		birthdayDate.setBounds(464, 182, 162, 19);
		contentPane.add(birthdayDate);

		JTextPane descriptionField = new JTextPane();
		descriptionField.setBounds(128, 182, 162, 89);
		contentPane.add(descriptionField);

		JLabel errorName = new JLabel("");
		errorName.setForeground(Color.RED);
		errorName.setFont(new Font("Arial", Font.PLAIN, 9));
		errorName.setBounds(464, 109, 90, 13);
		contentPane.add(errorName);

		JLabel errorDni = new JLabel("");
		errorDni.setForeground(Color.RED);
		errorDni.setFont(new Font("Arial", Font.PLAIN, 9));
		errorDni.setBounds(464, 155, 90, 13);
		contentPane.add(errorDni);

		JLabel errorAddress = new JLabel("");
		errorAddress.setForeground(Color.RED);
		errorAddress.setFont(new Font("Arial", Font.PLAIN, 9));
		errorAddress.setBounds(464, 285, 90, 13);
		contentPane.add(errorAddress);

		JLabel errorPhone = new JLabel("");
		errorPhone.setForeground(Color.RED);
		errorPhone.setFont(new Font("Arial", Font.PLAIN, 9));
		errorPhone.setBounds(464, 327, 90, 13);
		contentPane.add(errorPhone);

		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				if (name.isEmpty()) {
					errorName.setText("Campo obligatorio");
				} else {
					errorName.setText(null);
				}
				String dni = dniField.getText();
				if (dni.isEmpty()) {
					errorDni.setText("Campo obligatorio");
				} else {
					errorDni.setText(null);
				}
				String gender = (String) genderSelect.getSelectedItem();
				String address = addressField.getText();
				if (address.isEmpty()) {
					errorAddress.setText("Campo obligatorio");
				} else {
					errorAddress.setText(null);
				}
				String phone = phoneField.getText();

				if (phone.isEmpty()) {
					errorPhone.setText("Campo obligatorio");
					return;
				} else {
					errorPhone.setText(null);
				}

				Date selectedDate = birthdayDate.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = sdf.format(selectedDate);

				if (idHistoryMedical == 0) {
					JOptionPane.showMessageDialog(null, "Error: El ID del historial médico no está establecido");
					return;
				}

				if (dni.length() != 8) {
					JOptionPane.showMessageDialog(null, "El DNI es incorrecto. asegurese de ingresar datos reales");
					dniField.setText(null);
					return;
				}

				if (phone.length() != 9) {
					JOptionPane.showMessageDialog(null, "El celular es incorrecto. asegurese de ingresar datos reales");
					phoneField.setText(null);
					return;
				}

				if (dniExist(dni)) {
					JOptionPane.showMessageDialog(null, "El DNI ya ha sido registrado. Por favor, ingrese otro DNI.");
					dniField.setText(null);
					return;
				}

				try {
					String query = "INSERT INTO Patient (name, dni, dateOfBirth, gender, address, phone, MedicalHistory_id) VALUES (?,?,?,?,?,?,?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, dni);
					st.setString(3, formattedDate);
					st.setString(4, gender);
					st.setString(5, address);
					st.setString(6, phone);
					st.setInt(7, idHistoryMedical);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Paciente registrado correctamente");

					dispose();
					RegisterPatient createWindow = new RegisterPatient();
					createWindow.setLocationRelativeTo(null);
					createWindow.setVisible(true);
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}
			}
		});
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(396, 361, 125, 21);
		contentPane.add(registerBtn);

		nameField.setEnabled(false);
		dniField.setEnabled(false);
		genderSelect.setEnabled(false);
		addressField.setEnabled(false);
		phoneField.setEnabled(false);
		registerBtn.setEnabled(false);
		birthdayDate.setEnabled(false);
		
		JLabel descriptionError = new JLabel("");
		descriptionError.setForeground(new Color(255, 0, 0));
		descriptionError.setFont(new Font("Arial", Font.PLAIN, 10));
		descriptionError.setBounds(128, 270, 140, 13);
		contentPane.add(descriptionError);
		
		JLabel ageError = new JLabel("");
		ageError.setForeground(new Color(255, 0, 0));
		ageError.setBackground(new Color(255, 0, 0));
		ageError.setFont(new Font("Arial", Font.PLAIN, 10));
		ageError.setBounds(128, 155, 125, 13);
		contentPane.add(ageError);
		
		JButton btnNewButton = new JButton("REGISTRO HISTORIAL MÉDICO");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bloodType = (String) bloodTypeSelect.getSelectedItem();
				String age = ageField.getText();
				if (age.isEmpty()) {
					ageError.setText("Campo obligatorio");
				} else {
					ageError.setText(null);
				}
				String description = descriptionField.getText();

				if (description.isEmpty()) {
					descriptionError.setText("Campo obligatorio");
					return;
				} else {
					descriptionError.setText(null);
				}

				if (age.length() != 2) {
					JOptionPane.showMessageDialog(null, "La edad solo puede tener 2 digitos");
					ageField.setText(null);
					return;
				}

				try {
					String query = "INSERT INTO MedicalHistory(bloodType, age, description) VALUES (?,?,?)";
					PreparedStatement st = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					st.setString(1, bloodType);
					st.setString(2, age);
					st.setString(3, description);

					int affectedRows = st.executeUpdate();

					if (affectedRows > 0) {
						ResultSet generatedKeys = st.getGeneratedKeys();
						if (generatedKeys.next()) {
							idHistoryMedical = generatedKeys.getInt(1);
							JOptionPane.showMessageDialog(null, "Historial médico registrado correctamente");

							if (idHistoryMedical != 0) {
								nameField.setEnabled(true);
								dniField.setEnabled(true);
								genderSelect.setEnabled(true);
								addressField.setEnabled(true);
								phoneField.setEnabled(true);
								registerBtn.setEnabled(true);
								birthdayDate.setEnabled(true);

								bloodTypeSelect.setEnabled(false);
								ageField.setEnabled(false);
								descriptionField.setEnabled(false);
								btnNewButton.setEnabled(false);
							}
						}
					}
					ageField.setText(null);
					descriptionField.setText(null);

				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}

			}
		});
		btnNewButton.setBounds(49, 319, 219, 21);
		contentPane.add(btnNewButton);
	}

	private boolean dniExist(String dni) {
		try {
			String sql = "SELECT dni FROM Patient WHERE dni = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, dni);
			ResultSet result = statement.executeQuery();
			return result.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}