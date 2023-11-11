package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterMedicalHistory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ageField;
	private JTextField descriptionField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMedicalHistory frame = new RegisterMedicalHistory();
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
	public RegisterMedicalHistory() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterMedicalHistory.class.getResource("/img/logo.png")));
		setTitle("CLINICA DEL PILAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO HISTORIAL MEDICO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(83, 35, 468, 42);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(239, 248, 252));
		panel.setBounds(31, 86, 276, 303);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton registrarBtn = new JButton("REGISTRAR");
		registrarBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		registrarBtn.setBounds(10, 270, 131, 23);
		panel.add(registrarBtn);
		
		JButton regresarBtn = new JButton("REGRESAR");
		regresarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterPatient createWindow = new RegisterPatient();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		regresarBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		regresarBtn.setBounds(151, 270, 115, 23);
		panel.add(regresarBtn);
		
		JLabel lblBloodtype = new JLabel("<html>Tipo de <br>Sangre:</html>");
		lblBloodtype.setHorizontalAlignment(SwingConstants.LEFT);
		lblBloodtype.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBloodtype.setBounds(10, 55, 77, 34);
		panel.add(lblBloodtype);
		
		JLabel lblAge = new JLabel("Edad:");
		lblAge.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAge.setBounds(11, 111, 55, 14);
		panel.add(lblAge);
		
		JLabel lblDescription = new JLabel("Descripcion:");
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDescription.setBounds(10, 146, 87, 14);
		panel.add(lblDescription);
		
		ageField = new JTextField();
		ageField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});
		ageField.setBounds(96, 109, 170, 20);
		panel.add(ageField);
		ageField.setColumns(10);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(96, 144, 170, 95);
		panel.add(descriptionField);
		descriptionField.setColumns(10);
		
		JComboBox<String> bloodTypeSelect = new JComboBox<String>();
		bloodTypeSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		bloodTypeSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "A", "B", "AB", "Desconocido" }));
		bloodTypeSelect.setToolTipText("");
		bloodTypeSelect.setBounds(96, 67, 170, 22);
		panel.add(bloodTypeSelect);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(239, 248, 252));
		panel_1.setBounds(326, 86, 276, 303);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(RegisterMedicalHistory.class.getResource("/img/history.png")));
		lblNewLabel_3.setBounds(10, 11, 256, 281);
		panel_1.add(lblNewLabel_3);
	}
}
