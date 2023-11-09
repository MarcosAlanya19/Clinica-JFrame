package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

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
		setTitle("CLINICA DEL PILAR");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(181, 11, 273, 71);
		contentPane.add(lblNewLabel);
		
		JButton registroBtn = new JButton("REGISTRO");
		registroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registroBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		registroBtn.setBounds(141, 312, 126, 23);
		contentPane.add(registroBtn);
		
		JButton inicioBtn = new JButton("INICIA SESION");
		inicioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		inicioBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		inicioBtn.setBounds(405, 362, 126, 23);
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
		lblName.setBounds(68, 121, 84, 23);
		contentPane.add(lblName);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Arial", Font.PLAIN, 14));
		nameField.setBounds(170, 122, 176, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Arial", Font.PLAIN, 14));
		emailField.setColumns(10);
		emailField.setBounds(170, 156, 176, 20);
		contentPane.add(emailField);
		
		JLabel lblEmail = new JLabel("Correo:");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(68, 155, 84, 23);
		contentPane.add(lblEmail);
		
		JLabel lblPasword = new JLabel("Contraseña:");
		lblPasword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPasword.setBounds(68, 191, 84, 23);
		contentPane.add(lblPasword);
		
		JLabel lblPost = new JLabel("Cargo:");
		lblPost.setHorizontalAlignment(SwingConstants.LEFT);
		lblPost.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPost.setBounds(68, 263, 84, 23);
		contentPane.add(lblPost);
		
		JComboBox<String> postSelect = new JComboBox<String>();
		postSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		postSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Administrador", "Medico" }));
		postSelect.setToolTipText("");
		postSelect.setBounds(168, 263, 181, 22);
		contentPane.add(postSelect);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 193, 178, 20);
		contentPane.add(passwordField);
		
		JLabel lblPasword_1 = new JLabel("Validacion:");
		lblPasword_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasword_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPasword_1.setBounds(68, 225, 84, 23);
		contentPane.add(lblPasword_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(168, 227, 178, 20);
		contentPane.add(passwordField_1);
	}
}
