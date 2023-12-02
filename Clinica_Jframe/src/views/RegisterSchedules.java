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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import contructor.Doctor;
import model.DBConnection;

public class RegisterSchedules extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connect;
	JComboBox<Object> doctorSelect;
	MaskFormatter timeFormatter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterSchedules frame = new RegisterSchedules();
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
	public RegisterSchedules() {
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterSchedules.class.getResource("/img/logo.png")));
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
		lblNewLabel_1_1.setBounds(37, 161, 78, 13);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Hora fin:");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(37, 203, 66, 13);
		contentPane.add(lblNewLabel_1_2);
    
		try {
			timeFormatter = new MaskFormatter("##:##:##");
			timeFormatter.setPlaceholderCharacter('0');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RegisterSchedules.class.getResource("/img/appointmentSchedule.png")));
		lblNewLabel_2.setBounds(337, 79, 319, 257);
		contentPane.add(lblNewLabel_2);

		JComboBox<Object> daySelect = new JComboBox<Object>();
		daySelect.setFont(new Font("Arial", Font.PLAIN, 14));
		daySelect.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" }));
		daySelect.setBounds(132, 110, 153, 21);
		contentPane.add(daySelect);

		doctorSelect = new JComboBox<Object>();
		showDoctorSelect();
		doctorSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		doctorSelect.setBounds(132, 243, 153, 21);
		contentPane.add(doctorSelect);

		JLabel lblNewLabel_3 = new JLabel("Médico:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(37, 247, 78, 13);
		contentPane.add(lblNewLabel_3);

		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String day = (String) daySelect.getSelectedItem();
				String endTime = "hola";

				Doctor selectedDoctor = (Doctor) doctorSelect.getSelectedItem();

				
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

				try {
					Date parsedEndTime = timeFormat.parse(endTime);

	//				if (parsedStartTime.after(parsedEndTime)) {
						JOptionPane.showMessageDialog(null,
								"El tiempo de inicio no puede ser mayor que el tiempo de finalización.");
						return;
					

	//				String query = "INSERT INTO AppointmentSchedule (startTime, endTime, day, Doctor_id) VALUES (?, ?, ?, ?)";
	//				PreparedStatement st = connect.prepareStatement(query);
	//				st.setString(1, startTime);
		//			st.setString(2, endTime);
			//		st.setString(3, day);
				//	st.setLong(4, idDoctor);
					//st.executeUpdate();

			//		dispose();
				//	RegisterSchedules createWindow = new RegisterSchedules();
					//createWindow.setLocationRelativeTo(null);
			//		createWindow.setVisible(true);
				//	JOptionPane.showMessageDialog(null, "Horario registrado correctamente");
				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}
			}
		});
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(62, 330, 128, 21);
		contentPane.add(registerBtn);

		JButton backBtn = new JButton("REGRESAR");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterMedical createWindow = new RegisterMedical();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		backBtn.setFont(new Font("Arial", Font.BOLD, 14));
		backBtn.setBounds(223, 330, 123, 21);
		contentPane.add(backBtn);
		
		JComboBox<Object> hourSelect = new JComboBox<Object>();
		hourSelect.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		hourSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		hourSelect.setBounds(132, 161, 66, 21);
		contentPane.add(hourSelect);
		
		JComboBox<Object> minutSelect = new JComboBox<Object>();
		minutSelect.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		minutSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		minutSelect.setBounds(223, 161, 62, 21);
		contentPane.add(minutSelect);
		
		JComboBox<Object> hourSelect_2 = new JComboBox<Object>();
		hourSelect_2.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		hourSelect_2.setFont(new Font("Arial", Font.PLAIN, 14));
		hourSelect_2.setBounds(132, 200, 66, 21);
		contentPane.add(hourSelect_2);
		
		JComboBox<Object> minutSelect_2 = new JComboBox<Object>();
		minutSelect_2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		minutSelect_2.setFont(new Font("Arial", Font.PLAIN, 14));
		minutSelect_2.setBounds(223, 199, 62, 21);
		contentPane.add(minutSelect_2);
	}

	private void showDoctorSelect() {
		try {
			String query = "SELECT id, name FROM Doctor";
			PreparedStatement st = connect.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			doctorSelect.removeAllItems();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = String.format("Dr. %s", rs.getString("name"));
				Doctor doctor = new Doctor(id, name);
				doctorSelect.addItem(doctor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}