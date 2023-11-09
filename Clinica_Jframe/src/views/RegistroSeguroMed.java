package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class RegistroSeguroMed extends JFrame {

	private JPanel contentPane;
	private JTextField policyField;
	private JTextField detailsField;
	private JTextField companyField;
	private JButton backBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroSeguroMed frame = new RegistroSeguroMed();
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
	public RegistroSeguroMed() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 4500);
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
		lblNewLabel_1.setBounds(37, 129, 109, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Compañia de seguros:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(37, 205, 152, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Detalle de compañia:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(37, 279, 134, 13);
		contentPane.add(lblNewLabel_3);
		
		policyField = new JTextField();
		policyField.setBounds(200, 127, 134, 19);
		contentPane.add(policyField);
		policyField.setColumns(10);
		
		detailsField = new JTextField();
		detailsField.setBounds(200, 277, 134, 19);
		contentPane.add(detailsField);
		detailsField.setColumns(10);
		
		companyField = new JTextField();
		companyField.setBounds(199, 203, 134, 19);
		contentPane.add(companyField);
		companyField.setColumns(10);
		
		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(51, 403, 120, 21);
		contentPane.add(registerBtn);
		
		backBtn = new JButton("REGRESAR");
		backBtn.setFont(new Font("Arial", Font.BOLD, 14));
		backBtn.setBounds(209, 403, 125, 21);
		contentPane.add(backBtn);
	}

}
