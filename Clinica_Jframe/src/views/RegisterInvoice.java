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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import contructor.Patient;
import model.DBConnection;
import javax.swing.SwingConstants;

public class RegisterInvoice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField totalField;
	private Connection connect;
	JComboBox<Object> patientSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterInvoice frame = new RegisterInvoice();
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
	public RegisterInvoice() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterInvoice.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connect = DBConnection.getConnection();
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("REGISTRO FACTURA");
		lblNewLabel.setBounds(163, 10, 374, 30);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Fecha emision:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(32, 137, 130, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Total:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(32, 184, 45, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_5 = new JLabel("Detalle:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(32, 263, 83, 13);
		contentPane.add(lblNewLabel_5);

		totalField = new JTextField();
		totalField.setBounds(141, 182, 182, 19);
		contentPane.add(totalField);
		totalField.setColumns(10);

		JTextPane detailField = new JTextPane();
		detailField.setBounds(141, 250, 182, 69);
		contentPane.add(detailField);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(RegisterInvoice.class.getResource("/img/invoice.png")));
		lblNewLabel_6.setBounds(355, 50, 308, 297);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Paciente:");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(32, 92, 83, 13);
		contentPane.add(lblNewLabel_7);

		patientSelect = new JComboBox<Object>();
		showPatientSelect();
		patientSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		patientSelect.setBounds(141, 88, 182, 21);
		contentPane.add(patientSelect);

		JDateChooser issueDate = new JDateChooser();
		issueDate.setBounds(141, 137, 182, 19);
		contentPane.add(issueDate);

		JLabel subTotalLabel = new JLabel("");
		subTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subTotalLabel.setFont(new Font("Arial", Font.BOLD, 9));
		subTotalLabel.setBounds(278, 211, 45, 13);
		contentPane.add(subTotalLabel);

		JButton startBtn = new JButton("INICIO");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		startBtn.setFont(new Font("Arial", Font.BOLD, 14));
		startBtn.setBounds(430, 344, 116, 21);
		contentPane.add(startBtn);

		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String total = totalField.getText();
				String detail = detailField.getText();

				Date selectedDate = issueDate.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = sdf.format(selectedDate);

				Patient selectedPatient = (Patient) patientSelect.getSelectedItem();
				int patientId = selectedPatient.getId();

				try {
					String query = "INSERT INTO Invoice (issueDate, total, businessName, ruc, detail, Patient_id) VALUES (?, ?, ?, ?, ?,?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, formattedDate);
					st.setString(2, total);
					st.setString(3, "Clinica Maria del Pilar");
					st.setString(4, "20602477925");
					st.setString(5, detail);
					st.setLong(6, patientId);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Factura registrada correctamente");

				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en el servidor");
				}
			}
		});
		registerBtn.setBounds(152, 344, 116, 21);
		contentPane.add(registerBtn);

		JLabel igvLabel = new JLabel("");
		igvLabel.setHorizontalAlignment(SwingConstants.CENTER);
		igvLabel.setFont(new Font("Arial", Font.BOLD, 9));
		igvLabel.setBounds(278, 227, 45, 13);
		contentPane.add(igvLabel);

		JButton calculateBtn = new JButton("CALCULAR");
		calculateBtn.setFont(new Font("Arial", Font.BOLD, 8));
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat df = new DecimalFormat("#.##");
				double total = Double.parseDouble(totalField.getText());
				double subtotal = total / 1.18;
				double igv = subtotal * 0.18;
				String subTotalDecimal = df.format(subtotal);
				String igvDecimal = df.format(igv);
				subTotalLabel.setText(subTotalDecimal);
				igvLabel.setText(igvDecimal);
			}
		});
		calculateBtn.setBounds(141, 211, 89, 19);
		contentPane.add(calculateBtn);
	}

	private void showPatientSelect() {
		try {
			String query = "SELECT id, name FROM Patient";
			PreparedStatement st = connect.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			patientSelect.removeAllItems();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Patient patient = new Patient(id, name);
				patientSelect.addItem(patient);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
