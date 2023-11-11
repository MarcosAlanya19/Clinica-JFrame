package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DBConnection;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.ImageIcon;
import com.toedter.calendar.JDayChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.components.JLocaleChooser;

public class RegisterSchedules extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField startField;
	private JTextField endField;
	private Connection connect;

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
		
		startField = new JTextField();
		startField.setBounds(132, 159, 153, 19);
		contentPane.add(startField);
		startField.setColumns(10);
		
		endField = new JTextField();
		endField.setBounds(132, 201, 153, 19);
		contentPane.add(endField);
		endField.setColumns(10);
		
		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.setBounds(62, 330, 128, 21);
		contentPane.add(registerBtn);
		
		JButton backBtn = new JButton("INICIO");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		backBtn.setFont(new Font("Arial", Font.BOLD, 14));
		backBtn.setBounds(223, 330, 123, 21);
		contentPane.add(backBtn);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RegisterSchedules.class.getResource("/img/appointmentSchedule.png")));
		lblNewLabel_2.setBounds(337, 79, 319, 257);
		contentPane.add(lblNewLabel_2);
		
		JComboBox daySelect = new JComboBox();
		daySelect.setFont(new Font("Arial", Font.PLAIN, 14));
		daySelect.setModel(new DefaultComboBoxModel(new String[] {"Lunes", "", "Martes", "", "Miércoles", "", "Jueves", "", "Viernes", "", "Sábado", "", "Domingo"}));
		daySelect.setBounds(132, 110, 153, 21);
		contentPane.add(daySelect);
		
		JComboBox doctorSelect = new JComboBox();
		doctorSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		doctorSelect.setBounds(132, 243, 153, 21);
		contentPane.add(doctorSelect);
		
		JLabel lblNewLabel_3 = new JLabel("Médico:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(37, 247, 78, 13);
		contentPane.add(lblNewLabel_3);
	}
}
