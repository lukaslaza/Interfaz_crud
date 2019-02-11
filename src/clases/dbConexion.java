package clases;

import java.sql.*;

public class dbConexion {
	private static Connection con = null;

	public static Connection getConnection() {
		try {
			if (con == null) {
				//String driver = "com.mysql.cj.jdbc.Driver"; // el driver varia segun la DB que usemos
				String url = "jdbc:mariadb://localhost:2222/taller";
				String pwd = "";
				String usr = "root";
				//Class.forName(driver);
				con = DriverManager.getConnection(url, usr, pwd);
				System.out.println("Conection Succesfull");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) {
getConnection();
	}
}