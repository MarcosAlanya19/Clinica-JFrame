package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import appConfig.AppConfig;

public class DBConnection {
	private static final String SERVER_URL = AppConfig.SERVER_URL;
	private static final String USER = AppConfig.USER;
	private static final String PASSWORD = AppConfig.PASSWORD;

	public static Connection getConnection() {
		Connection connection = null;
		String message = null;

		try {
			connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
			message = "Conexión a la base de datos exitosa.";
		} catch (SQLException e) {
			message = "Error al conectar a la base de datos: " + e.getMessage();
			showErrorDialog(message);
		}

		System.out.println(message);

		return connection;
	}

	private static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error de conexión", JOptionPane.ERROR_MESSAGE);
	}
}
