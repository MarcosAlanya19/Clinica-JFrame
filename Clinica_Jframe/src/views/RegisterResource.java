package views;

//import java.awt.Color;}
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.DBConnection;


public class RegisterResource extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField statusField;
	private Connection connect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterResource frame = new RegisterResource();
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
	public RegisterResource() {
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterResource.class.getResource("/img/logo.png")));
		setTitle("CLINICA DEL PILAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton registroBtn = new JButton("REGISTRO");
		registroBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		registroBtn.setBounds(42, 263, 115, 23);
		contentPane.add(registroBtn);
		
		JButton regresarBtn = new JButton("REGRESAR");
		regresarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterMedical createWindow = new RegisterMedical();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		regresarBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		regresarBtn.setBounds(185, 263, 123, 23);
		contentPane.add(regresarBtn);
		


		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 157, 65, 14);
		contentPane.add(lblNewLabel);

		nameField = new JTextField();
		nameField.setBounds(135, 151, 173, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEstado.setBounds(42, 185, 65, 14);
		contentPane.add(lblEstado);

		statusField = new JTextField();
		statusField.setColumns(10);
		statusField.setBounds(135, 179, 173, 20);
		contentPane.add(statusField);

		JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
		lblDisponibilidad.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDisponibilidad.setBounds(42, 213, 105, 14);
		contentPane.add(lblDisponibilidad);

		JComboBox<String> availabilitySelect = new JComboBox<String>();
		availabilitySelect.setFont(new Font("Arial", Font.PLAIN, 14));
		availabilitySelect.setModel(new DefaultComboBoxModel<String>(new String[] { "Mañana", "Tarde", "Noche" }));
		availabilitySelect.setToolTipText("");
		availabilitySelect.setBounds(135, 210, 173, 22);
		contentPane.add(availabilitySelect);

		JLabel lblNewLabel_1 = new JLabel("REGISTRO RECURSOS");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(155, 54, 340, 36);
		contentPane.add(lblNewLabel_1);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		//JButton registroBtn = new JButton("REGISTRO");
		registroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String statu = statusField.getText();
				String availability = (String) availabilitySelect.getSelectedItem();
				//String idDoctor = (String) idDoctorSelect.getSelectedItem();

				if (name.isEmpty() || statu.isEmpty() || availability.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
					return;
				}

				try {
					String query = "INSERT INTO Resource (name, status, availability, Doctor_id) VALUES (?, ?, ?, ?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, statu);
					st.setString(3, availability);
					//st.setString(4, idDoctor);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Paciente registrado correctamente");
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}
			}
		});
		registroBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		registroBtn.setBounds(42, 263, 115, 23);
		contentPane.add(registroBtn);

		//JButton regresarBtn = new JButton("REGRESAR");
		regresarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		regresarBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		regresarBtn.setBounds(185, 263, 123, 23);
		contentPane.add(regresarBtn);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(RegisterResource.class.getResource("/img/resource.png")));
		lblNewLabel_2.setBounds(332, 101, 265, 276);
		contentPane.add(lblNewLabel_2);
	}
}
