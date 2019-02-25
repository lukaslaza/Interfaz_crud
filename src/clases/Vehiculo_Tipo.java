package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Vehiculo_Tipo {
	
	//VALORES
	private String id, descripcion;

	//CONSTRUCTORES
	public Vehiculo_Tipo(String id, String descripcion) {
		setId(id);
		setDescripcion(descripcion);
	}
	public Vehiculo_Tipo() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void insertarse() {
		String query = "INSERT INTO vehiculo_tipo (id, descripcion) VALUES(?,?)";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, getId());
			stmt.setString(2, getDescripcion());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar el Tipo de Vehiculo");
		}
	}
	
	public static void modificar(Vehiculo_Tipo vehiculo_tipo) {
		String query = "UPDATE vehiculo_tipo SET descripcion = '?' WHERE id like '?'";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, vehiculo_tipo.getDescripcion());
			stmt.setString(2, vehiculo_tipo.getId());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al modificar el Tipo de Vehiculo");
		}
	}

	public void borrarse() {
		String query = " Delete from vehiculo_tipo where id like '"+getId()+"' ";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al borrar el Tipo de Vehiculo");
		}
	}

}
