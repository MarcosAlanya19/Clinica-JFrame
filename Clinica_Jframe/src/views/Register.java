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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import model.DBConnection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField emailField;
	private Connection connect;
	private JPasswordField passwordField;
	private JPasswordField passwordVerifyField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setBackground(new Color(255, 255, 255));
		connect = DBConnection.getConnection();
		setTitle("CLINICA DEL PILAR");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> postSelect = new JComboBox<String>();
		postSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		postSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Administrador", "Medico" }));
		postSelect.setToolTipText("");
		postSelect.setBounds(168, 278, 181, 22);
		contentPane.add(postSelect);

		JLabel lblNewLabel = new JLabel("REGISTRO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(181, 11, 273, 71);
		contentPane.add(lblNewLabel);
		
		JLabel errorName = new JLabel("");
		errorName.setForeground(Color.RED);
		errorName.setFont(new Font("Arial", Font.PLAIN, 9));
		errorName.setBounds(168, 114, 166, 14);
		contentPane.add(errorName);
		
		JLabel errorEmail = new JLabel("");
		errorEmail.setForeground(new Color(255, 0, 0));
		
		contentPane.add(errorEmail);
		
		JLabel errorPassword = new JLabel("");
		errorPassword.setForeground(Color.RED);
		errorPassword.setFont(new Font("Arial", Font.PLAIN, 9));
		errorPassword.setBounds(168, 202, 166, 14);
		contentPane.add(errorPassword);
		
		JLabel erroEmail = new JLabel("");
		erroEmail.setForeground(Color.RED);
		errorEmail.setFont(new Font("Arial", Font.PLAIN, 9));
		errorEmail.setBounds(168, 159, 166, 14);
		contentPane.add(erroEmail);
		
		JLabel errorVerify = new JLabel("");
		errorVerify.setFont(new Font("Arial", Font.PLAIN, 9));
		errorVerify.setForeground(Color.RED);
		errorEmail.setFont(new Font("Arial", Font.PLAIN, 9));
		errorVerify.setBounds(168, 247, 166, 14);
		contentPane.add(errorVerify);
		
		
		JButton registrarBtn = new JButton("REGISTRAR");
		registrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				
				if (name.isEmpty()) {
					errorName.setText("Campo Obligatorio");
				} else {
					errorName.setText(null);
				}
				
				String email = emailField.getText();
				
				if (email.isEmpty()) {
					erroEmail.setText("Campo Obligatorio");
					
				} else {
					erroEmail.setText(null);
				}
				String password = String.valueOf(passwordField.getPassword());
				
				if (password.isEmpty()) {
					errorPassword.setText("Campo Obligatorio");
				} else {
					errorPassword.setText(null);
				}
				
				String hashedPassword = hashPassword(password);
				String verifyPassword = String.valueOf(passwordVerifyField.getPassword());

				if (verifyPassword.isEmpty()) {
					errorVerify.setText("Campo Obligatorio");
				} else {
					errorVerify.setText(null);
				}
				
				String selectedRol = (String) postSelect.getSelectedItem();
				
				

				if (!password.contentEquals(verifyPassword)) {
					errorPassword.setText("Las contraseñas no coinciden");
					return;
				}
				
				
				String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
				Pattern pattern = Pattern.compile(emailRegex);
				Matcher matcher = pattern.matcher(email);

				if (!matcher.matches()) {
					errorEmail.setText("El correo electrónico no es válido");
					return;
				}else {
					errorEmail.setText(null);
				}

				if (matcher.matches()) {
					if (emailExist(email)) {
						errorEmail.setText("El email ya ha sido registrado. Por favor, ingrese otro email.");
						return;
					}else {
						errorEmail.setText(null);
					}
				}
				

				try {
					String query = "INSERT INTO admin (name, email, password, rol) VALUES (?,?,?,?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, email);
					st.setString(3, hashedPassword);
					st.setString(4, selectedRol);
					st.executeUpdate();

					System.out.println(selectedRol);

					JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
					nameField.setText(null);
					emailField.setText(null);
					passwordField.setText(null);
					passwordVerifyField.setText(null);

				} catch (Exception err) {
					err.printStackTrace();
				}

			}
		});
		registrarBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registrarBtn.setBounds(141, 312, 126, 23);
		contentPane.add(registrarBtn);

		JButton inicioBtn = new JButton("INICIA SESION");
		inicioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login createWindow = new Login();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		inicioBtn.setFont(new Font("Arial", Font.BOLD, 14));
		inicioBtn.setBackground(new Color(239, 248, 252));
		inicioBtn.setForeground(new Color(0, 0, 0));
		inicioBtn.setBounds(405, 362, 200, 23);
		contentPane.add(inicioBtn);

		JLabel lblNewLabel_1 = new JLabel("¿Ya tienes una cuenta?");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(168, 362, 178, 23);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Register.class.getResource("/img/register.png")));
		lblNewLabel_2.setBounds(356, 93, 247, 256);
		contentPane.add(lblNewLabel_2);

		JLabel lblName = new JLabel("Nombres:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblName.setBounds(68, 93, 84, 23);
		contentPane.add(lblName);

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
		nameField.setFont(new Font("Arial", Font.PLAIN, 14));
		nameField.setBounds(170, 94, 176, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		emailField = new JTextField();
		emailField.setFont(new Font("Arial", Font.PLAIN, 14));
		emailField.setColumns(10);
		emailField.setBounds(170, 139, 176, 20);
		contentPane.add(emailField);

		JLabel lblEmail = new JLabel("Correo:");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(68, 138, 84, 23);
		contentPane.add(lblEmail);

		JLabel lblPasword = new JLabel("Contraseña:");
		lblPasword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPasword.setBounds(68, 179, 84, 23);
		contentPane.add(lblPasword);

		JLabel lblPost = new JLabel("Cargo:");
		lblPost.setHorizontalAlignment(SwingConstants.LEFT);
		lblPost.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPost.setBounds(68, 278, 84, 23);
		contentPane.add(lblPost);

		passwordField = new JPasswordField();
		passwordField.setBounds(168, 181, 178, 20);
		contentPane.add(passwordField);

		JLabel lblPasword_1 = new JLabel("Validacion:");
		lblPasword_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasword_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPasword_1.setBounds(68, 225, 84, 23);
		contentPane.add(lblPasword_1);

		passwordVerifyField = new JPasswordField();
		passwordVerifyField.setBounds(168, 227, 178, 20);
		contentPane.add(passwordVerifyField);
		


	}

	private static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	private boolean emailExist(String email) {
		try {
			String sql = "SELECT email FROM admin WHERE email = ?";
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			return result.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
