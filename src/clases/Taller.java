package clases;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.MariaDbStatement;

public class Taller {
	// Variables.
	private String nombre, direccion, telefono, latitud, longitud;

	public Taller(String nombre, String direccion, String telefono, String latitud, String longitud) {

		setDireccion(direccion);
		setLatitud(latitud);
		setTelefono(telefono);
		setNombre(nombre);
		setLongitud(longitud);
	}

	public static void getTalleres() {
		Connection cn = dbConexion.getConnection();
		try {
			MariaDbStatement st = (MariaDbStatement) cn.createStatement();
			String sql1 = "SELECT * FROM taller";
			ResultSet rs = st.executeQuery(sql1);

		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al intentar selecionar los talleres");
			e.printStackTrace();
		}

	}

	public static void newTaller(String nombre, String direccion, String telefono, String latitud, String longitud) {
		Connection cn = dbConexion.getConnection();
		//MariaDbStatement sentencia;
		Statement sentencia;
		try {
			MariaDbStatement st = (MariaDbStatement) cn.createStatement();
			String sql1 = "Insert into taller VALUES (?,?,?,?,?)";
			sentencia = (Statement) cn.prepareStatement(sql1);
			sentencia.setString(1, "Gimeno");
			sentencia.setString(2, "Guardamar del segura, poligono de Santan Ana");
			sentencia.setString(3, "966728227");
			sentencia.setString(4, "1924384");
			sentencia.setString(5, "2442424");
			sentencia.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al intentar selecionar los talleres");
			e.printStackTrace();
		}

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public static void main(String[] args) {
		getTalleres();
	}
}
