package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DBConnection;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterAdm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField lastnameField;
	private JTextField dniField;
	private JTextField phoneField;
	private JTextField emailField;
	private Connection connect;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAdm frame = new RegisterAdm();
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
	public RegisterAdm() {
		connect = DBConnection.getConnection();
		setType(Type.UTILITY);
		setTitle("RegisterAdm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				try {
					int key = e.getKeyChar();

			    boolean mayusculas = key >= 65 && key <= 90;
			    boolean minusculas = key >= 97 && key <= 122;
			    boolean espacio = key == 32;
			            
			     if (!(minusculas || mayusculas || espacio))
			    {
			        e.consume();
			    }
				}catch(Exception c) {
				}
				
			}
		});
		nameField.setBounds(215, 81, 138, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		lastnameField = new JTextField();
		lastnameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					int key = e.getKeyChar();

			    boolean mayusculas = key >= 65 && key <= 90;
			    boolean minusculas = key >= 97 && key <= 122;
			    boolean espacio = key == 32;
			            
			     if (!(minusculas || mayusculas || espacio))
			    {
			        e.consume();
			    }
				}catch(Exception c) {
				}
			}
		});
		lastnameField.setColumns(10);
		lastnameField.setBounds(215, 120, 138, 20);
		contentPane.add(lastnameField);
		
		dniField = new JTextField();
		dniField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					int key = e.getKeyChar();

			    boolean numeros = key >= 48 && key <= 57;
			        
			    if (!numeros)
			    {
			        e.consume();
			    }

			    if (dniField.getText().trim().length() == 8) {
			        e.consume();
			    }
				}catch(Exception b) {
				}
			}
		});
		dniField.setColumns(10);
		dniField.setBounds(267, 188, 86, 20);
		contentPane.add(dniField);
		
		phoneField = new JTextField();
		phoneField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				try {
					int key = e.getKeyChar();

			    boolean numeros = key >= 48 && key <= 57;
			        
			    if (!numeros)
			    {
			        e.consume();
			    }

			    if (phoneField.getText().trim().length() == 9) {
			        e.consume();
			    }
				}catch(Exception b) {
				}
			}
		});
		phoneField.setColumns(10);
		phoneField.setBounds(267, 219, 86, 20);
		contentPane.add(phoneField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(215, 250, 138, 20);
		contentPane.add(emailField);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(110, 87, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastname = new JLabel("Apellidos");
		lblLastname.setBounds(110, 126, 46, 14);
		contentPane.add(lblLastname);
		
		JLabel lblGender = new JLabel("Genero");
		lblGender.setBounds(110, 160, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(110, 191, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblPhone = new JLabel("Celular");
		lblPhone.setBounds(110, 225, 46, 14);
		contentPane.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(110, 253, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(110, 287, 66, 14);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_1 = new JLabel("REGISTRO");
		lblNewLabel_1.setBounds(190, 37, 56, 22);
		contentPane.add(lblNewLabel_1);
		
		JComboBox<String> genderSelect = new JComboBox<String>();
		genderSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"Masculino", "Femenino"}));
		genderSelect.setToolTipText("");
		genderSelect.setBounds(267, 155, 86, 22);
		contentPane.add(genderSelect);
		
		JButton RegisterButton = new JButton("Registrar");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String lastname = lastnameField.getText();
				String dni = dniField.getText();
				String phone = phoneField.getText();
				String email = emailField.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				String selectedGender = (String) genderSelect.getSelectedItem();
				String genderMapping = "Masculino".equals(selectedGender) ? "Male" : "Female";
				System.out.println("Género seleccionado: " + genderMapping);
				
				try {
					String query = "INSERT INTO administrative (name, lastname, dni, phone, email, password, gender) VALUES (?,?,?,?,?,?,?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, lastname);
					st.setString(3, dni);
					st.setString(4, phone);
					st.setString(5, email);
					st.setString(6, password);
					st.setString(7, genderMapping);
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registrado");

					
					
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		RegisterButton.setBounds(189, 330, 89, 23);
		contentPane.add(RegisterButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(267, 284, 86, 20);
		contentPane.add(passwordField);
	}
}
