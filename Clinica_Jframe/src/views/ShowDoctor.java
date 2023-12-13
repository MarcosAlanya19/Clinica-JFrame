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
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.DBConnection;

public class ShowDoctor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connect;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textIdDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowDoctor frame = new ShowDoctor();
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
	public ShowDoctor() {
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
		btnLoadData.setBounds(656, 89, 118, 49);
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		contentPane.add(btnLoadData);

		JLabel lblNewLabel = new JLabel("LISTA DE MEDICOS");
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
		btnNewButton.setBounds(656, 335, 118, 49);
		contentPane.add(btnNewButton);

		JButton btnDelete = new JButton("ELIMINAR");
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBounds(656, 253, 118, 25);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textIdDelete.getText();

				try {
					String query = "DELETE FROM doctor WHERE id = ?";
					PreparedStatement st = connect.prepareStatement(query);
					st.setString(1, id);

					int rowsDeleted = st.executeUpdate();

					if (rowsDeleted > 0) {
						JOptionPane.showMessageDialog(null,
								"La fila con ID " + id + " ha sido eliminada exitosamente.");
					} else {
						JOptionPane.showMessageDialog(null,
								"No se encontró ninguna fila con ID " + id + " para eliminar.");
					}

					st.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		contentPane.add(btnDelete);

		textIdDelete = new JTextField();
		textIdDelete.setBounds(656, 222, 118, 20);
		contentPane.add(textIdDelete);
		textIdDelete.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID para eliminar");
		lblNewLabel_1.setBounds(657, 199, 105, 20);
		contentPane.add(lblNewLabel_1);
	}

	private void fillTable() {
		try {
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor");

			tableModel.setRowCount(0);

			int columnCount = resultSet.getMetaData().getColumnCount();

			tableModel.setColumnCount(0);

			for (int i = 1; i <= columnCount; i++) {
				String originalColumnName = resultSet.getMetaData().getColumnName(i);
				String newColumnName;

				switch (originalColumnName.toLowerCase()) {
				case "id":
					newColumnName = "Codigo";
					break;
				case "name":
					newColumnName = "Nombre";
					break;
				case "phone":
					newColumnName = "Telefono";
				case "email":
					newColumnName = "Correo";
					break;
				case "address":
					newColumnName = "Dirección";
					break;
				default:
					newColumnName = originalColumnName;
					break;
				}
				tableModel.addColumn(newColumnName);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
