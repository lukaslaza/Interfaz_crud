package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public static String[] getClientesMeta() {

		String query = "Select * from cliente";
		Connection conn = (Connection) dbConexion.getConnection();
		ArrayList<String> metadatos = new ArrayList<String>();
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			// EJECUTAMLS SENTENCIA
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
				// La metemos en un array
				metadatos.add(rs.getMetaData().getColumnName(x).toUpperCase());
			}
			metadatos.add("Seleccionar".toUpperCase());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String[] cabeceras = new String[metadatos.size()];

		for (int i = 0; i < metadatos.size(); i++) {
			cabeceras[i] = metadatos.get(i);
		}
		return cabeceras;
	}

	public static Object[][] getClientes(int ini, int fin) {
		// Sentencia
		String query = "Select * from cliente LIMIT " + ini + " , " + fin;
		// COnexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				Cliente c1 = new Cliente(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("telefono"),rs.getString("direccion"),rs.getString("id_usuario"));
				clientes.add(c1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Object[][] datosCliente = new Object[fin - ini][7];
		for (int i = 0; i < clientes.size(); i++) {
			datosCliente[i][0] = clientes.get(i).getDni();
			datosCliente[i][1] = clientes.get(i).getNombre();
			datosCliente[i][2] = clientes.get(i).getApellidos();
			datosCliente[i][3] = clientes.get(i).getTelefono();
			datosCliente[i][4] = clientes.get(i).getDireccion();
			datosCliente[i][5] = clientes.get(i).getId_usuario();
			datosCliente[i][6] = new Boolean(false);
		}

		return datosCliente;

	}

}
