package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import model.DBConnection;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class RegisterInvoice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField totalField;
	private Connection connect;

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
		lblNewLabel_2.setBounds(32, 230, 45, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_5 = new JLabel("Detalle:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(32, 263, 83, 13);
		contentPane.add(lblNewLabel_5);

		totalField = new JTextField();
		totalField.setBounds(141, 246, 182, 19);
		contentPane.add(totalField);
		totalField.setColumns(10);

		JTextPane detailField = new JTextPane();
		detailField.setBounds(141, 275, 182, 69);
		contentPane.add(detailField);

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
		startBtn.setBounds(56, 354, 116, 21);
		contentPane.add(startBtn);

		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issueDate = dateOfIssueField.getText();
				String ruc = rucField.getText();
				String businessName = businessNameField.getText();
				String total = totalField.getText();
				String detail = detailField.getText();
				String patient = patientField.getText();

				try {
					String query = "INSERT INTO Invoice (issueDate, total, businessName, ruc, detail, Patient_id) VALUES (?, ?, ?, ?, ?,?)";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, issueDate);
					st.setString(2, total);
					st.setString(3, businessName);
					st.setString(4, ruc);
					st.setString(5, detail);
					st.setString(6, patient);
					st.executeUpdate();

					JOptionPane.showMessageDialog(null, "Factura registrada correctamente");

				} catch (Exception err) {
					err.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		registerBtn.setBounds(220, 354, 116, 21);
		contentPane.add(registerBtn);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(RegisterInvoice.class.getResource("/img/invoice.png")));
		lblNewLabel_6.setBounds(355, 50, 308, 297);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Paciente:");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(32, 92, 83, 13);
		contentPane.add(lblNewLabel_7);
		
		JComboBox patientSelect = new JComboBox();
		patientSelect.setFont(new Font("Arial", Font.PLAIN, 14));
		patientSelect.setBounds(141, 88, 182, 21);
		contentPane.add(patientSelect);
		
		JDateChooser issueDate = new JDateChooser();
		issueDate.setBounds(141, 137, 182, 19);
		contentPane.add(issueDate);
		
		JLabel label = new JLabel("New label");
		label.setBounds(554, 359, 45, 13);
		contentPane.add(label);
	}
}
