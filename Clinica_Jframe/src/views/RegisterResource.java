package views;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import contructor.Doctor;
import model.DBConnection;
import java.awt.event.KeyAdapter;

public class RegisterResource extends JFrame {

	private static final long serialVersionUID = 1L;
	private Connection connect;
	private JPanel contentPane;
	private JTextField nameField;

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
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(239, 248, 252));
		panel_1.setBounds(331, 87, 293, 313);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(RegisterResource.class.getResource("/img/resource.png")));
		lblNewLabel_1.setBounds(0, 0, 293, 313);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(239, 248, 252));
		panel_2.setBounds(10, 11, 614, 65);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO RECURSOS");
		lblNewLabel.setBackground(new Color(239, 248, 252));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 0, 614, 65);
		panel_2.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Nombre:");
		lblName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblName.setBounds(20, 121, 81, 14);
		contentPane.add(lblName);
		
		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
		});
		nameField.setColumns(10);
		nameField.setBounds(123, 119, 170, 20);
		contentPane.add(nameField);
		
		JLabel lblEstatus = new JLabel("Estado:");
		lblEstatus.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEstatus.setBounds(20, 152, 81, 14);
		contentPane.add(lblEstatus);
		
		JComboBox<String> statusSelect = new JComboBox<String>();
		statusSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"En uso", "Mantenimiento"}));
		statusSelect.setToolTipText("");
		statusSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		statusSelect.setBounds(123, 150, 170, 22);
		contentPane.add(statusSelect);
		
		JLabel lblAvailability = new JLabel("Disponibilidad:");
		lblAvailability.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAvailability.setBounds(20, 184, 93, 14);
		contentPane.add(lblAvailability);
		
		JComboBox<String> availabilitySelect = new JComboBox<String>();
		availabilitySelect.setModel(new DefaultComboBoxModel<String>(new String[] {"Mañana", "Tarde", "Noche" }));
		availabilitySelect.setToolTipText("");
		availabilitySelect.setFont(new Font("Arial", Font.PLAIN, 14));
		availabilitySelect.setBounds(123, 180, 170, 22);
		contentPane.add(availabilitySelect);
		
		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDoctor.setBounds(20, 215, 81, 14);
		contentPane.add(lblDoctor);
		
		JComboBox<String> Doctor_idSelect = new JComboBox<String>();
		Doctor_idSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"Disponible", "ocupado"}));
		Doctor_idSelect.setToolTipText("");
		Doctor_idSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		Doctor_idSelect.setBounds(123, 211, 170, 22);
		contentPane.add(Doctor_idSelect);
		
		JButton btnNewButton = new JButton("REGISTRO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String status = (String)statusSelect.getSelectedItem();
				String availability = (String) availabilitySelect.getSelectedItem();
				String idDoctor = (String) Doctor_idSelect.getSelectedItem();

				if (name.isEmpty() || status.isEmpty() || availability.isEmpty() || idDoctor.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
					return;
				}

				try {
					String query = "INSERT INTO Resource (name, status, availability, Doctor_id) VALUES (?, ?, ?, ?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, status);
					st.setString(3, availability);
					st.setString(4, idDoctor);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Paciente registrado correctamente");
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(32, 320, 115, 23);
		contentPane.add(btnNewButton);
		
		JButton btnRegresar = new JButton("REGRESAR");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterMedical createWindow = new RegisterMedical ();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		btnRegresar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRegresar.setBounds(157, 320, 115, 23);
		contentPane.add(btnRegresar);
		
		
	}
}
