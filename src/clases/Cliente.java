package clases;

import java.awt.Component;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cliente {

	// Variables
	private String dni, nombre, apellidos, telefono, direccion, id_usuario;

	public Cliente(String dni, String nombre, String apellidos, String telefono, String direccion, String id_usuario) {
		setDireccion(direccion);
		setDni(dni);
		setNombre(nombre);
		setApellidos(apellidos);
		setTelefono(telefono);
		setId_usuario(id_usuario);
	}
	public Cliente(String dni) {
		setDni(dni);
	}

	public Cliente() {

	}

	// SET AND GET
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void insertarse() {
		String query = "INSERT INTO cliente (dni, nombre, apellidos, telefono, direccion, id_usuario) VALUES(?,?,?,?,?,?)";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, getDni());
			stmt.setString(2, getNombre());
			stmt.setString(3, getApellidos());
			stmt.setString(4, getTelefono());
			stmt.setString(5, getDireccion());
			stmt.setString(6, getId_usuario());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar el Cliente");
		}
	}
	
	public static void modificar(Cliente cliente) {
		String query = "UPDATE cliente SET nombre = '?', apellidos = '?', telefono = '?', "
				+ "direccion = '?', id_usuario = '?' WHERE dni like '?'";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellidos());
			stmt.setString(3, cliente.getTelefono());
			stmt.setString(4, cliente.getDireccion());
			stmt.setString(5, cliente.getId_usuario());
			stmt.setString(6, cliente.getDni());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar el Cliente");
		}
	}

	public void borrarse() {
		String query = " Delete from cliente where dni like '?' ";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, getDni());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al borrar el Cliente");
		}
	}

}
