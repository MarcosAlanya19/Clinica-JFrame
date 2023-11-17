package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.DBConnection;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ShowPatient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connect;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPatient frame = new ShowPatient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowPatient() {
		connect = DBConnection.getConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShowPatient.class.getResource("/img/logo.png")));
		setTitle("Vista de pacientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 248, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tableModel = new DefaultTableModel();
		contentPane.setLayout(null);
		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 89, 629, 295);
		contentPane.add(scrollPane);

		JButton btnLoadData = new JButton("<html><div style='text-align:center;'>CARGAR <br/> DATOS</div></html>");
		btnLoadData.setFont(new Font("Arial", Font.BOLD, 14));
		btnLoadData.setBounds(656, 154, 118, 49);
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		contentPane.add(btnLoadData);
		
		JLabel lblNewLabel = new JLabel("LISTA DE PACIENTE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(167, 27, 354, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("INICIO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home createWindow = new Home();
				createWindow.setLocationRelativeTo(null);
				createWindow.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBounds(656, 234, 118, 49);
		contentPane.add(btnNewButton);
	}

	private void fillTable() {
		try {
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM patient");

			tableModel.setRowCount(0);

			int columnCount = resultSet.getMetaData().getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
				tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
			}

			while (resultSet.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = resultSet.getObject(i);
				}
				tableModel.addRow(row);
			}

			resultSet.close();
			statement.close();
			connect.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}