package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class RegistroPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dniField;
	private JTextField birthdateField;
	private JTextField addressField;
	private JTextField numberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroPaciente frame = new RegistroPaciente();
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
	public RegistroPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(179, 93, 142, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("REGISTRO PACIENTE");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(179, 30, 362, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("DNI:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(179, 139, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha nacimiento:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(179, 182, 142, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Género:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(179, 226, 85, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Dirección:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(179, 269, 114, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Celular:");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(179, 309, 85, 13);
		contentPane.add(lblNewLabel_6);
		
		nameField = new JTextField();
		nameField.setBounds(331, 91, 162, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		dniField = new JTextField();
		dniField.setBounds(331, 137, 162, 19);
		contentPane.add(dniField);
		dniField.setColumns(10);
		
		birthdateField = new JTextField();
		birthdateField.setBounds(331, 180, 162, 19);
		contentPane.add(birthdateField);
		birthdateField.setColumns(10);
		
		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(171, 361, 125, 21);
		contentPane.add(registerBtn);
		
		JButton startBtn = new JButton("INICIO");
		startBtn.setFont(new Font("Arial", Font.BOLD, 14));
		startBtn.setBounds(379, 361, 114, 21);
		contentPane.add(startBtn);
		
		JComboBox<String> genderSelect = new JComboBox<String>();
		genderSelect.setFont(new Font("Arial", Font.PLAIN, 11));
		genderSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Masculino", "Femenino" }));
		genderSelect.setToolTipText("");
		genderSelect.setBounds(331, 222, 162, 22);
		contentPane.add(genderSelect);
		
		addressField = new JTextField();
		addressField.setBounds(331, 267, 162, 19);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		numberField = new JTextField();
		numberField.setBounds(331, 307, 162, 19);
		contentPane.add(numberField);
		numberField.setColumns(10);
	}
}
