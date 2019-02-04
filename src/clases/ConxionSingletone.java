package clases;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Deny Prasetyo
 */
public class ConxionSingletone {

	private static ConxionSingletone instance;
	private Connection connection;
	private String url = "jdbc:postgresql://localhost:2222/taller";
	private String username = "root";
	private String password = "";

	private ConxionSingletone() throws SQLException {
	        try {
	            Class.forName("org.postgresql.Driver");
	            this.connection = DriverManager.getConnection(url, username, password);
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
	        }
	    }

	public Connection getConnection() {
		return connection;
	}

	public static ConxionSingletone getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConxionSingletone();
		} else if (instance.getConnection().isClosed()) {
			instance = new ConxionSingletone();
		}

		return instance;
	}

}