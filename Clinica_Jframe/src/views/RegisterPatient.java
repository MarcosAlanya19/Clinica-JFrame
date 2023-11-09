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
import java.sql.Statement;

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

import model.DBConnection;

public class RegisterPatient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dniField;
	private JTextField birthdateField;
	private JTextField addressField;
	private JTextField phoneField;
	private Connection connect;
	private JTextField ageField;
	private int id;

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
		nameField.setBounds(464, 91, 162, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);

		dniField = new JTextField();
		dniField.setBounds(464, 137, 162, 19);
		contentPane.add(dniField);
		dniField.setColumns(10);

		birthdateField = new JTextField();
		birthdateField.setBounds(464, 180, 162, 19);
		contentPane.add(birthdateField);
		birthdateField.setColumns(10);
		
		JComboBox<String> genderSelect = new JComboBox<String>();
		genderSelect.setFont(new Font("Arial", Font.PLAIN, 11));
		genderSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Masculino", "Femenino" }));
		genderSelect.setToolTipText("");
		genderSelect.setBounds(464, 222, 162, 22);
		contentPane.add(genderSelect);

		addressField = new JTextField();
		addressField.setBounds(464, 267, 162, 19);
		contentPane.add(addressField);
		addressField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setBounds(464, 307, 162, 19);
		contentPane.add(phoneField);
		phoneField.setColumns(10);

		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String dni = dniField.getText();
				String birthdate = birthdateField.getText();
				String gender =(String) genderSelect.getSelectedItem();
				String address = addressField.getText();
				String phone = phoneField.getText();


				try {
					String query = "INSERT INTO Patient (name, dni, dateOfBirth, gender, address, phone, MedicalHistory_id) VALUES (?,?,?,?,?,?,?)";
		            PreparedStatement st = connect.prepareStatement(query);
		            st.setString(1, name);
		            st.setString(2, dni);
		            st.setString(3, birthdate);
		            st.setString(4, gender);
		            st.setString(5, address);
		            st.setString(6, phone);
		            st.setInt(7, id);
		            st.executeUpdate();

					
					JOptionPane.showMessageDialog(null,"Paciente registrado correctamente");
					nameField.setText(null);
					dniField.setText(null);
					birthdateField.setText(null);
					addressField.setText(null);
					phoneField.setText(null);
					
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}

			}
		});
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(396, 361, 125, 21);
		contentPane.add(registerBtn);

		JButton startBtn = new JButton("INICIO");
		startBtn.setFont(new Font("Arial", Font.BOLD, 14));
		startBtn.setBounds(91, 361, 114, 21);
		contentPane.add(startBtn);
		
		
		ageField = new JTextField();
		ageField.setBounds(76, 161, 162, 19);
		contentPane.add(ageField);
		ageField.setColumns(10);
		
		JComboBox<String> bloodTypeSelect = new JComboBox<String>();
		bloodTypeSelect.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "AB", "O"}));
		bloodTypeSelect.setToolTipText("");
		bloodTypeSelect.setFont(new Font("Arial", Font.PLAIN, 11));
		bloodTypeSelect.setBounds(76, 109, 162, 22);
		contentPane.add(bloodTypeSelect);
		
		JTextPane descriptionField = new JTextPane();
		descriptionField.setBounds(76, 197, 162, 89);
		contentPane.add(descriptionField);
		
		JButton btnNewButton = new JButton("REGISTRO HISTORIAL MÉDICO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bloodType =(String) bloodTypeSelect.getSelectedItem();
				String age = ageField.getText();
				String description = descriptionField.getText();
				
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
		                    int id = generatedKeys.getInt(1);
		                    // Ahora puedes usar 'id' como desees
		                    JOptionPane.showMessageDialog(null, "Historial médico registrado correctamente. ID: " + id);
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
		btnNewButton.setBounds(33, 306, 205, 21);
		contentPane.add(btnNewButton);
	}
}
