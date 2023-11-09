package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
public class RegisterMedical extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField addressField;
	private JLabel lblEmail;
	

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterMedical.class.getResource("/img/logo.png")));
		setTitle("CLINICA DEL PILAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton registroBtn = new JButton("REGISTRO");
		registroBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		registroBtn.setBounds(60, 274, 117, 23);
		contentPane.add(registroBtn);
		
		JButton inicioBtn = new JButton("INICIO");
		inicioBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		inicioBtn.setBounds(210, 274, 89, 23);
		contentPane.add(inicioBtn);
		
		JButton asignarRecursosBtn = new JButton("<html>ASIGNAR<br>RECURSOS</html>");
		asignarRecursosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterResource createWindow = new RegisterResource();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		asignarRecursosBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		asignarRecursosBtn.setBounds(341, 346, 107, 54);
		contentPane.add(asignarRecursosBtn);
		
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
		nameField.setBounds(139, 133, 192, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblPhone = new JLabel("Celular:");
		lblPhone.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPhone.setBounds(40, 163, 68, 17);
		contentPane.add(lblPhone);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(139, 162, 192, 20);
		contentPane.add(phoneField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(139, 191, 192, 20);
		contentPane.add(emailField);
		
		lblEmail = new JLabel("Correo:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(40, 192, 68, 17);
		contentPane.add(lblEmail);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(139, 221, 192, 20);
		contentPane.add(addressField);
		
		JLabel lblAddress = new JLabel("Direccion:");
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAddress.setBounds(40, 222, 68, 17);
		contentPane.add(lblAddress);
		
		JButton asignarHorariosBtn = new JButton("<html>ASIGNAR<br>HORARIOS</html>");
		asignarHorariosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterSchedules createWindow = new RegisterSchedules();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		asignarHorariosBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		asignarHorariosBtn.setBounds(490, 346, 107, 54);
		contentPane.add(asignarHorariosBtn);
	}
	
}
