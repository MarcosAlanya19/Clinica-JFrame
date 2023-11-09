package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	private JTextField numberField;
	private Connection connect;

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
		
		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(396, 361, 125, 21);
		contentPane.add(registerBtn);
		
		JButton startBtn = new JButton("INICIO");
		startBtn.setFont(new Font("Arial", Font.BOLD, 14));
		startBtn.setBounds(91, 361, 114, 21);
		contentPane.add(startBtn);
		
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
		
		numberField = new JTextField();
		numberField.setBounds(464, 307, 162, 19);
		contentPane.add(numberField);
		numberField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Antes de registrar un paciente, tiene ");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(37, 110, 205, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("que registrar un historial médico:");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(51, 125, 191, 13);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("REGISTRO HISTORIAL MÉDICO");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBounds(10, 148, 259, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_9 = new JLabel("Despues de registrar un paciente, puede");
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(10, 237, 232, 13);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("asignar seguro medico.");
		lblNewLabel_10.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(55, 251, 162, 13);
		contentPane.add(lblNewLabel_10);
		
		JButton btnAsignarSeguroMdico = new JButton("ASIGNAR SEGURO MÉDICO");
		btnAsignarSeguroMdico.setFont(new Font("Arial", Font.BOLD, 14));
		btnAsignarSeguroMdico.setBounds(10, 275, 259, 21);
		contentPane.add(btnAsignarSeguroMdico);
	}
}
