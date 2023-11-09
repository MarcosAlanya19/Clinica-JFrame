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
public class RegistroMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroMedico frame = new RegistroMedico();
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
	public RegistroMedico() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroMedico.class.getResource("/img/logo.png")));
		setTitle("CLINICA DEL PILAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("REGISTRO");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(60, 274, 117, 23);
		contentPane.add(btnNewButton);
		
		JButton btnInicio = new JButton("INICIO");
		btnInicio.setFont(new Font("Arial", Font.PLAIN, 14));
		btnInicio.setBounds(210, 274, 89, 23);
		contentPane.add(btnInicio);
		
		JButton btnAsignar = new JButton("<html>ASIGNAR<br>RECURSOS</html>");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAsignar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAsignar.setBounds(341, 346, 107, 54);
		contentPane.add(btnAsignar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RegistroMedico.class.getResource("/img/regismedico.png")));
		lblNewLabel.setBounds(341, 81, 256, 254);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("REGISTRO MEDICO");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(153, 32, 297, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombres:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(40, 134, 68, 17);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(139, 133, 192, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Celular:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(40, 163, 68, 17);
		contentPane.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(139, 162, 192, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(139, 191, 192, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Correo:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(40, 192, 68, 17);
		contentPane.add(lblNewLabel_2_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(139, 221, 192, 20);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Direccion:");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1_2.setBounds(40, 222, 68, 17);
		contentPane.add(lblNewLabel_2_1_2);
		
		JButton btnAsignar_1 = new JButton("<html>ASIGNAR<br>HORARIOS</html>");
		btnAsignar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAsignar_1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAsignar_1.setBounds(490, 346, 107, 54);
		contentPane.add(btnAsignar_1);
	}
	
}
