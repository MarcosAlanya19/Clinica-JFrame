package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.DBConnection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegisterHealthInsurance extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField policyField;
	private JTextField detailsField;
	private JButton backBtn;
	private JLabel lblNewLabel_4;
	private Connection connect;
	private JComboBox patientSelect;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterHealthInsurance frame = new RegisterHealthInsurance();
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
	public RegisterHealthInsurance() {
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterHealthInsurance.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SEGURO MEDICO");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(165, 23, 334, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Número poliza");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 112, 109, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Compañia de seguros:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(38, 158, 152, 17);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Detalle de compañia:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(37, 205, 134, 13);
		contentPane.add(lblNewLabel_3);
		
		policyField = new JTextField();
		policyField.setBounds(200, 112, 134, 19);
		contentPane.add(policyField);
		policyField.setColumns(10);
		
		detailsField = new JTextField();
		detailsField.setBounds(200, 203, 134, 19);
		contentPane.add(detailsField);
		detailsField.setColumns(10);
		
		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(128, 326, 125, 21);
		contentPane.add(registerBtn);
		
		backBtn = new JButton("INICIO");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		backBtn.setFont(new Font("Arial", Font.BOLD, 14));
		backBtn.setBounds(128, 364, 125, 21);
		contentPane.add(backBtn);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(RegisterHealthInsurance.class.getResource("/img/healthlnsurance.png")));
		lblNewLabel_4.setBounds(344, 91, 292, 256);
		contentPane.add(lblNewLabel_4);
		
		patientSelect = new JComboBox();
		patientSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		patientSelect.setBounds(200, 247, 134, 21);
		contentPane.add(patientSelect);
		
		lblNewLabel_5 = new JLabel("Paciente:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(37, 251, 76, 13);
		contentPane.add(lblNewLabel_5);
		
		JComboBox insuranceCompañySelect = new JComboBox();
		insuranceCompañySelect.setFont(new Font("Arial", Font.PLAIN, 14));
		insuranceCompañySelect.setModel(new DefaultComboBoxModel(new String[] {"SANITAS", "MAPFRE", "RIMAC", "PACÍFICO", "ONCOSALUD", "LA POSITIVA"}));
		insuranceCompañySelect.setBounds(200, 157, 134, 21);
		contentPane.add(insuranceCompañySelect);
	}
}