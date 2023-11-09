package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

public class RegistroRecurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroRecurso frame = new RegistroRecurso();
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
	public RegistroRecurso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroRecurso.class.getResource("/img/logo.png")));
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
		btnNewButton.setBounds(62, 296, 105, 23);
		contentPane.add(btnNewButton);
		
		JButton btnRecurso = new JButton("RECURSO");
		btnRecurso.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRecurso.setBounds(205, 296, 105, 23);
		contentPane.add(btnRecurso);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(62, 86, 65, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(155, 80, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEstado.setBounds(62, 114, 65, 14);
		contentPane.add(lblEstado);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(155, 108, 173, 20);
		contentPane.add(textField_1);
		
		JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
		lblDisponibilidad.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDisponibilidad.setBounds(62, 142, 105, 14);
		contentPane.add(lblDisponibilidad);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCodigo.setBounds(62, 170, 65, 14);
		contentPane.add(lblCodigo);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(155, 168, 173, 20);
		contentPane.add(textField_3);
		
		JComboBox<String> genderSelect = new JComboBox<String>();
		genderSelect.setFont(new Font("Arial", Font.PLAIN, 11));
		genderSelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Mañana", "Tarde", "Noche" }));
		genderSelect.setToolTipText("");
		genderSelect.setBounds(155, 139, 173, 22);
		contentPane.add(genderSelect);
	}

}
