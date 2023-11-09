package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class RegistroHorarios extends JFrame {

	private JPanel contentPane;
	private JTextField dayField;
	private JTextField startField;
	private JTextField endField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHorarios frame = new RegistroHorarios();
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
	public RegistroHorarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HORARIO DE CITAS");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(162, 25, 305, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Día:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 113, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hora inicio:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(37, 177, 78, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Hora fin:");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(37, 243, 66, 13);
		contentPane.add(lblNewLabel_1_2);
		
		dayField = new JTextField();
		dayField.setBounds(162, 111, 123, 19);
		contentPane.add(dayField);
		dayField.setColumns(10);
		
		startField = new JTextField();
		startField.setBounds(162, 175, 123, 19);
		contentPane.add(startField);
		startField.setColumns(10);
		
		endField = new JTextField();
		endField.setBounds(162, 241, 123, 19);
		contentPane.add(endField);
		endField.setColumns(10);
		
		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(62, 330, 128, 21);
		contentPane.add(registerBtn);
		
		JButton backBtn = new JButton("REGRESAR");
		backBtn.setFont(new Font("Arial", Font.BOLD, 14));
		backBtn.setBounds(223, 330, 123, 21);
		contentPane.add(backBtn);
	}

}
