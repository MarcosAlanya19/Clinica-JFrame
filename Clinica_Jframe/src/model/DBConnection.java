  package model;
  
  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.SQLException;
  
  import javax.swing.JOptionPane;
  
  public class DBConnection {
  	private static final String DATABASE_NAME = "clinica_del_pilar";
  	private static final String SERVER_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
  	private static final String USER = "root";
  	private static final String PASSWORD = "root";
  
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
