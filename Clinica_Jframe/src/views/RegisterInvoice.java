package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import model.DBConnection;

public class RegisterInvoice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField issueField;
	private JTextField rucField;
	private JTextField reasonField;
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
		lblNewLabel_1.setBounds(32, 78, 130, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Total:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(32, 212, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Razón social:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(32, 166, 99, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("RUC:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(32, 122, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Detalle:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(32, 253, 83, 13);
		contentPane.add(lblNewLabel_5);
		
		issueField = new JTextField();
		issueField.setBounds(142, 76, 182, 19);
		contentPane.add(issueField);
		issueField.setColumns(10);
		
		rucField = new JTextField();
		rucField.setBounds(142, 120, 182, 19);
		contentPane.add(rucField);
		rucField.setColumns(10);
		
		reasonField = new JTextField();
		reasonField.setBounds(141, 164, 182, 19);
		contentPane.add(reasonField);
		reasonField.setColumns(10);
		
		totalField = new JTextField();
		totalField.setBounds(142, 210, 182, 19);
		contentPane.add(totalField);
		totalField.setColumns(10);
		
		JTextPane detailTextPane = new JTextPane();
		detailTextPane.setBounds(142, 253, 182, 69);
		contentPane.add(detailTextPane);
		
		JButton startBtn = new JButton("INICIO");
		startBtn.setFont(new Font("Arial", Font.BOLD, 14));
		startBtn.setBounds(228, 354, 116, 21);
		contentPane.add(startBtn);
		
		JButton registerBtn = new JButton("REGISTRO");
		registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registerBtn.setBounds(68, 354, 116, 21);
		contentPane.add(registerBtn);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(RegisterInvoice.class.getResource("/img/invoice.png")));
		lblNewLabel_6.setBounds(355, 50, 308, 297);
		contentPane.add(lblNewLabel_6);
	}
}
