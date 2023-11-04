package views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DBConnection;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegisterPatient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dniField;
	private JTextField phoneField;
	private JTextField emailField;
	private JButton btnNewButton;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(250, 50, 86, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JTextField lastNameField = new JTextField();
		lastNameField.setBounds(250, 81, 86, 20);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);
		
		dniField = new JTextField();
		dniField.setBounds(250, 146, 86, 20);
		contentPane.add(dniField);
		dniField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setBounds(250, 183, 86, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(250, 212, 86, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JComboBox<String> genderSelect = new JComboBox<String>();
		genderSelect.setFont(new Font("Arial", Font.PLAIN, 11));
		genderSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Masculino", "Femenino" }));
		genderSelect.setBounds(250, 112, 86, 22);
		contentPane.add(genderSelect);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String lastname = lastNameField.getText();
				String dni = dniField.getText();
				String phone = phoneField.getText();
				String email = emailField.getText();
				
				String selectedGender = (String) genderSelect.getSelectedItem();
				String genderMapping = "Masculino".equals(selectedGender) ? "Male" : "Female";
				
				try {
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setBounds(158, 278, 89, 23);
		contentPane.add(btnNewButton);
	}
}
