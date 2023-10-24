package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import contructor.User;
import model.DBConnection;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private Connection connect;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		connect = DBConnection.getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		usernameField = new JTextField();
		usernameField.setBounds(55, 49, 86, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JButton LoginBtn = new JButton("Iniciar sesión");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());

				try {
					String query = "SELECT * FROM Administrative WHERE dni = ? AND password = ?";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, username);
					st.setString(2, password);

					ResultSet rs = st.executeQuery();
					User authenticatedUser = null;

					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. Bienvenido, " + username + "!");
						int userId = rs.getInt("id");
						authenticatedUser = new User(userId, username);
					} else {
						JOptionPane.showMessageDialog(null, "Inicio de sesión fallido. Usuario o contraseña incorrectos.");
					}

					rs.close();
					st.close();
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en la base de datos");
				}
			}
		});
		LoginBtn.setBounds(102, 209, 121, 23);
		contentPane.add(LoginBtn);

		passwordField = new JPasswordField();
		passwordField.setBounds(189, 140, 7, 20);
		contentPane.add(passwordField);
	}
}
