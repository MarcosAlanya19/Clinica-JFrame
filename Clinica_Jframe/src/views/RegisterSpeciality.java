package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DBConnection;

public class RegisterSpeciality extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField descriptionField;
	private JTextField priceField;
	private Connection connect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterSpeciality frame = new RegisterSpeciality();
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
	public RegisterSpeciality() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterSpeciality.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registro especialidad");
		setBackground(new Color(240, 240, 240));
		connect = DBConnection.getConnection();
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {int key = e.getKeyChar();
				boolean mayusculas=key >=65 && key<=90;
				boolean minuscula=key >=97 && key<=122;
				boolean espacio = key ==32;
				if (!(mayusculas|| minuscula|| espacio)) {e.consume();}	
				}catch (Exception e3) {
					// TODO: handle exception
				}
				}
			}
		);
		nameField.setBounds(160, 78, 154, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);

		descriptionField = new JTextField();
		descriptionField.setBounds(160, 119, 154, 19);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);

		priceField = new JTextField();
		priceField.addKeyListener(new KeyAdapter() {

	@Override
			public void keyTyped(KeyEvent e) {
				try {double key = e.getKeyChar();
				boolean numeros=key >=48 && key <=57;
				if(!numeros) {e.consume();}
				}catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		priceField.setBounds(160, 159, 154, 19);
		contentPane.add(priceField);
		priceField.setColumns(10);

		JLabel lblNewLabel = new JLabel("REGISTRO ESPECIALIDAD");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 28));
		lblNewLabel.setBounds(73, 23, 426, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(56, 80, 124, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Descripcion:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(56, 121, 105, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Precio:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(56, 161, 76, 13);
		contentPane.add(lblNewLabel_3);

		JButton registerButton = new JButton("REGISTRAR");
		registerButton.setFont(new Font("Arial", Font.BOLD, 14));
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String description = descriptionField.getText();
				String price = priceField.getText();

				try {
					String query = "INSERT INTO speciality(name, description, price) VALUES(?, ?, ?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, description);
					st.setString(3, price);
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registro exitoso");

				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en registros");
				}
			}
		});
		registerButton.setBounds(125, 210, 123, 19);
		contentPane.add(registerButton);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(RegisterSpeciality.class.getResource("/img/speciality.png")));
		lblNewLabel_5.setBounds(358, 23, 254, 230);
		contentPane.add(lblNewLabel_5);
	}
}
