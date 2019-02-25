package clases;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.print.Book;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.crypto.Data;

import org.mariadb.jdbc.MariaDbStatement;

public class Taller {

	// Valores de la clase
	private String nombre, direccion, telefono;
	private float latitud, longitud;

	// Constructores
	public Taller(String nombre, String direccion, String telefono, float latitud, float longitud) {
		setDireccion(direccion);
		setLongitud(latitud);
		setTelefono(telefono);
		setNombre(nombre);
		setLongitud(longitud);
	}

	public Taller() {
	}
	// GET

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public float getLatitud() {
		return latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	// SET
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public void insertarse() {
		String query = "INSERT INTO taller (nombre, direccion, telefono, latitud, longitud) VALUES(?,?,?,?,?)";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, getNombre());
			stmt.setString(2, getDireccion());
			stmt.setString(3, getTelefono());
			stmt.setFloat(4, getLatitud());
			stmt.setFloat(5, getLongitud());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar el Taller");
		}
	}

	public static void modificar(Taller taller) {
		String query = "UPDATE taller SET direccion = '?', telefono = '?', latitud = '?', "
				+ "longitud = '?' WHERE nombre like '?'";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, taller.getDireccion());
			stmt.setString(2, taller.getTelefono());
			stmt.setFloat(3, taller.getLatitud());
			stmt.setFloat(4, taller.getLongitud());
			stmt.setString(5, taller.getNombre());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar el Taller");
		}
	}

	public void borrarse() {
		String query = " Delete from taller where nombre like '"+ getNombre()+"' ";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			//stmt.setString(1, getNombre());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al borrar el Taller");
		}
	}

}
