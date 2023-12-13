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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.DBConnection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterMedical extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JLabel lblEmail;

	private Connection connect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMedical frame = new RegisterMedical();
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
	public RegisterMedical() {
		setBackground(new Color(255, 255, 255));
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterMedical.class.getResource("/img/logo.png")));
		setTitle("CLINICA DEL PILAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton asignarRecursosBtn = new JButton("<html>ASIGNAR<br>RECURSOS</html>");
		asignarRecursosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterResource createWindow = new RegisterResource();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		asignarRecursosBtn.setFont(new Font("Arial", Font.BOLD, 14));
		asignarRecursosBtn.setBounds(341, 346, 107, 54);
		contentPane.add(asignarRecursosBtn);

		JButton asignarHorariosBtn = new JButton("<html>ASIGNAR<br>HORARIOS</html>");
		asignarHorariosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterSchedules createWindow = new RegisterSchedules();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		asignarHorariosBtn.setFont(new Font("Arial", Font.BOLD, 14));
		asignarHorariosBtn.setBounds(490, 346, 107, 54);
		contentPane.add(asignarHorariosBtn);

		JLabel errorAddress = new JLabel("");
		errorAddress.setForeground(Color.RED);
		errorAddress.setFont(new Font("Arial", Font.PLAIN, 9));
		errorAddress.setBounds(139, 295, 166, 14);
		contentPane.add(errorAddress);

		JLabel erName = new JLabel("");
		erName.setForeground(Color.RED);
		erName.setFont(new Font("Arial", Font.PLAIN, 9));
		erName.setBounds(139, 157, 166, 14);
		contentPane.add(erName);

		JLabel errorPhone = new JLabel("");
		errorPhone.setForeground(new Color(255, 0, 0));
		errorPhone.setFont(new Font("Arial", Font.PLAIN, 9));
		errorPhone.setBounds(139, 204, 166, 14);
		contentPane.add(errorPhone);

		JLabel errorEmail = new JLabel("");
		errorEmail.setForeground(new Color(255, 0, 0));
		errorEmail.setFont(new Font("Arial", Font.PLAIN, 9));
		errorEmail.setBounds(139, 251, 166, 14);
		contentPane.add(errorEmail);

		JButton registroBtn = new JButton("REGISTRO");
		registroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String phone = phoneField.getText();
				String email = emailField.getText();
				String address = addressField.getText();

				boolean hasError = false;

				if (name.isEmpty()) {
					erName.setText("Campo Obligatorio");
					hasError = true;
				} else {
					erName.setText(null);
				}

				if (phone.isEmpty()) {
					errorPhone.setText("Campo Obligatorio");
					hasError = true;
				} else if (phone.length() != 9) {
					errorPhone.setText("El celular es incorrecto. Asegúrese de ingresar solo 9 dígitos");
					hasError = true;
				} else {
					errorPhone.setText(null);
				}

				if (email.isEmpty()) {
					errorEmail.setText("Campo Obligatorio");
					hasError = true;
				} else {
					String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
					Pattern pattern = Pattern.compile(emailRegex);
					Matcher matcher = pattern.matcher(email);

					if (!matcher.matches()) {
						errorEmail.setText("El correo electrónico no es válido");
						emailField.setText(null);
						hasError = true;
					} else {
						errorEmail.setText(null);

						if (emailExist(email)) {
							errorEmail.setText("El email ya ha sido registrado. Por favor, ingrese otro email.");
							hasError = true;
						}
					}
				}

				if (address.isEmpty()) {
					errorAddress.setText("Campo Obligatorio");
					hasError = true;
				} else {
					errorAddress.setText(null);
				}

				if (hasError) {
					return;
				}

				try {
					String query = "INSERT INTO doctor (name, phone, email, address) VALUES (?,?,?,?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, phone);
					st.setString(3, email);
					st.setString(4, address);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Medico registrado correctamente");
					nameField.setText(null);
					phoneField.setText(null);
					emailField.setText(null);
					addressField.setText(null);

					asignarHorariosBtn.setEnabled(true);
					asignarRecursosBtn.setEnabled(true);

				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}

			}

			private boolean emailExist(String email) {
				try {
					String sql = "SELECT email FROM doctor WHERE email = ?";
					PreparedStatement statement = connect.prepareStatement(sql);
					statement.setString(1, email);
					ResultSet result = statement.executeQuery();
					return result.next();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		});
		registroBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registroBtn.setBounds(60, 329, 117, 23);
		contentPane.add(registroBtn);

		JButton inicioBtn = new JButton("INICIO");
		inicioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		inicioBtn.setFont(new Font("Arial", Font.BOLD, 14));
		inicioBtn.setBounds(210, 329, 89, 23);
		contentPane.add(inicioBtn);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegisterMedical.class.getResource("/img/registerMedical.png")));
		lblNewLabel.setBounds(341, 81, 256, 254);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("REGISTRO MEDICO");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(153, 32, 297, 38);
		contentPane.add(lblNewLabel_1);

		JLabel lblName = new JLabel("Nombres:");
		lblName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblName.setBounds(40, 134, 68, 17);
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
		nameField.setBounds(139, 133, 192, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel lblPhone = new JLabel("Celular:");
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPhone.setBounds(40, 183, 68, 17);
		contentPane.add(lblPhone);

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
		phoneField.setBounds(139, 182, 192, 20);
		contentPane.add(phoneField);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(139, 229, 192, 20);
		contentPane.add(emailField);

		lblEmail = new JLabel("Correo:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(40, 230, 68, 17);
		contentPane.add(lblEmail);

		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(139, 276, 192, 20);
		contentPane.add(addressField);

		JLabel lblAddress = new JLabel("Direccion:");
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAddress.setBounds(40, 277, 68, 17);
		contentPane.add(lblAddress);

		boolean doctorsEmpty = checkIfTableIsEmpty("Doctor");

		asignarHorariosBtn.setEnabled(!doctorsEmpty);
		asignarRecursosBtn.setEnabled(!doctorsEmpty);

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
