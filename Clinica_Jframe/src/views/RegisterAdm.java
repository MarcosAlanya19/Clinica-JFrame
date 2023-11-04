package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import model.DBConnection;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RegisterAdm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField lastnameField;
	private JTextField dniField;
	private JTextField phoneField;
	private JTextField emailField;
	private Connection connect;
	private JPasswordField passwordField;
	private JPasswordField verifyPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAdm frame = new RegisterAdm();
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
	public RegisterAdm() {
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterAdm.class.getResource("/img/logo.png")));
		connect = DBConnection.getConnection();
		setTitle("Registro de Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 464);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		nameField.setBounds(185, 92, 168, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		lastnameField = new JTextField();
		lastnameField.addKeyListener(new KeyAdapter() {
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
		lastnameField.setColumns(10);
		lastnameField.setBounds(185, 123, 168, 20);
		contentPane.add(lastnameField);

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
		dniField.setColumns(10);
		dniField.setBounds(185, 191, 168, 20);
		contentPane.add(dniField);

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
		phoneField.setColumns(10);
		phoneField.setBounds(185, 222, 168, 20);
		contentPane.add(phoneField);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(185, 253, 168, 20);
		contentPane.add(emailField);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(82, 92, 66, 20);
		contentPane.add(lblNewLabel);

		JLabel lblLastname = new JLabel("Apellidos:");
		lblLastname.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLastname.setBounds(82, 123, 66, 20);
		contentPane.add(lblLastname);

		JLabel lblGender = new JLabel("Genero:");
		lblGender.setFont(new Font("Arial", Font.PLAIN, 14));
		lblGender.setBounds(82, 158, 86, 20);
		contentPane.add(lblGender);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDni.setBounds(82, 191, 46, 20);
		contentPane.add(lblDni);

		JLabel lblPhone = new JLabel("Celular:");
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPhone.setBounds(82, 222, 66, 20);
		contentPane.add(lblPhone);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(82, 253, 46, 20);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPassword.setBounds(82, 284, 94, 20);
		contentPane.add(lblPassword);

		JLabel lblNewLabel_1 = new JLabel("REGISTRO");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(214, 34, 208, 31);
		contentPane.add(lblNewLabel_1);

		JComboBox<String> genderSelect = new JComboBox<String>();
		genderSelect.setFont(new Font("Arial", Font.PLAIN, 11));
		genderSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Masculino", "Femenino" }));
		genderSelect.setToolTipText("");
		genderSelect.setBounds(185, 155, 168, 22);
		contentPane.add(genderSelect);

		JButton RegisterButton = new JButton("REGISTRAR");
		RegisterButton.setFont(new Font("Arial", Font.BOLD, 11));
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String lastname = lastnameField.getText();
				String dni = dniField.getText();
				String phone = phoneField.getText();
				String email = emailField.getText();

				String password = String.valueOf(passwordField.getPassword());
				String hashedPassword = hashPassword(password);
				String verifyPassword = String.valueOf(verifyPasswordField.getPassword());

				String selectedGender = (String) genderSelect.getSelectedItem();
				String genderMapping = "Masculino".equals(selectedGender) ? "Male" : "Female";

				if (name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
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

				String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
				Pattern pattern = Pattern.compile(emailRegex);
				Matcher matcher = pattern.matcher(email);

				if (!matcher.matches()) {
					JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
					emailField.setText(null);
					return;
				}

				if (!password.equals(verifyPassword)) {
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
					return;
				}

				try {
					String query = "INSERT INTO administrative (name, lastname, dni, phone, email, password, gender) VALUES (?,?,?,?,?,?,?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, lastname);
					st.setString(3, dni);
					st.setString(4, phone);
					st.setString(5, email);
					st.setString(6, hashedPassword);
					st.setString(7, genderMapping);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Administrador registrado correctamente");
					nameField.setText(null);
					lastnameField.setText(null);
					dniField.setText(null);
					phoneField.setText(null);
					emailField.setText(null);
					passwordField.setText(null);
					verifyPasswordField.setText(null);
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}
			}

			private boolean dniExist(String dni) {
				try {
					String sql = "SELECT dni FROM administrative WHERE dni = ?";
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

		);

		RegisterButton.setBounds(167, 375, 112, 23);
		contentPane.add(RegisterButton);

		passwordField = new JPasswordField();
		passwordField.setBounds(185, 284, 168, 20);
		contentPane.add(passwordField);

		verifyPasswordField = new JPasswordField();
		verifyPasswordField.setBounds(185, 315, 168, 20);
		contentPane.add(verifyPasswordField);

		JLabel lblNewLabel_2 = new JLabel("Validacion:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(82, 315, 86, 20);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(RegisterAdm.class.getResource("/img/Adm.png")));
		lblNewLabel_3.setBounds(363, 92, 240, 256);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_2_1 = new JLabel("¿ya tienes una cuenta?");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(302, 375, 157, 20);
		contentPane.add(lblNewLabel_2_1);

		JButton btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login createWindow = new Login();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 11));
		btnIniciarSesion.setBounds(466, 375, 112, 23);
		contentPane.add(btnIniciarSesion);
	}

	private static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
}