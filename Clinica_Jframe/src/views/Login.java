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

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Connection connect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("INICIAR SESION");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(42, 35, 292, 36);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(63, 104, 113, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("INICIO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				char[] passwordChars = passwordField.getPassword();
				String password = new String(passwordChars);

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, ingrese tanto el nombre de usuario como la contraseña.");
					return;
				}

				if (!isValidEmail(username)) {
					JOptionPane.showMessageDialog(null, "Por favor, ingrese un correo electrónico válido.");
					return;
				}

				try {
					String query = "SELECT * FROM administrative WHERE email = ?";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, username);

					ResultSet rs = st.executeQuery();

					if (rs.next()) {
						String hashedPasswordFromDatabase = rs.getString("password");
						System.out.println("Contraseña en la base de datos: " + hashedPasswordFromDatabase);

						if (BCrypt.checkpw(password, hashedPasswordFromDatabase)) {
							dispose();
							Home createWindow = new Home();
							createWindow.setLocationRelativeTo(null);
							createWindow.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado");
					}

					st.close();

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error durante la autenticación.");
				}
			}

			private boolean isValidEmail(String email) {
				String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
				return email.matches(regex);
			}

		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBounds(148, 182, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1_1 = new JLabel("Contraseña:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(63, 141, 113, 14);
		contentPane.add(lblNewLabel_1_1);

		usernameField = new JTextField();
		usernameField.setBounds(184, 102, 135, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/img/login.png")));
		lblNewLabel_2.setBounds(371, 35, 240, 239);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("¿No tienes cuenta?");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(63, 248, 165, 14);
		contentPane.add(lblNewLabel_3);

		JButton registerBtn = new JButton("Registrate");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterAdm createWindow = new RegisterAdm();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		registerBtn.setBackground(new Color(239, 248, 252));
		registerBtn.setForeground(new Color(0, 0, 0));
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(193, 244, 126, 23);
		contentPane.add(registerBtn);

		passwordField = new JPasswordField();
		passwordField.setBounds(184, 139, 135, 20);
		contentPane.add(passwordField);
	}
}
