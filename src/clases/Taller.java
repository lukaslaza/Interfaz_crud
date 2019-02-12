package clases;

import java.awt.print.Book;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.xml.crypto.Data;

import org.mariadb.jdbc.MariaDbStatement;

public class Taller {
	// Variables.
	private String nombre, direccion, telefono;
	private float latitud, longitud;

	public Taller(String nombre, String direccion, String telefono, float latitud, float longitud) {

		setDireccion(direccion);
		setLongitud(latitud);
		setTelefono(telefono);
		setNombre(nombre);
		setLongitud(longitud);
	}

	/**
	 * Funcion que nos devulve los metadatos de la tabla talleres es decir las
	 * cabeceras de la tabla
	 * 
	 * @return
	 */
	public static String[] getTalleresMeta() {

		String query = "Select * from taller";
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
		
		for (int i = 0; i < metadatos.size();i++) {
			cabeceras[i] = metadatos.get(i);
		}
		return cabeceras;
	}

	
	/**
	 * Nos devuelve los metadatos de la tabla taller pero en formato arrayList
	 * 
	 * @return
	 */
	public static ArrayList<String> getTalleresMetaArr() {

		String query = "Select * from taller";
		Connection conn = (Connection) dbConexion.getConnection();
		ArrayList<String> metadatos = new ArrayList<>();
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

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return metadatos;

	}

	/**
	 * Funcion que nos devuelve todos los talleres en formato arrayList
	 * 
	 * @return
	 */
	public static ArrayList<Taller> getTalleresArr() {
		String query = "Select * from taller order by nombre asc";
		Connection conn = (Connection) dbConexion.getConnection();
		ArrayList<Taller> talleres = new ArrayList<>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				String nombreTaller = rs.getString("nombre");
				String direccionTaller = rs.getString("direccion");
				String telefonoTaller = rs.getString("telefono");
				float latitudTaller = rs.getFloat("latitud");
				float longitudTaller = rs.getFloat("longitud");
				Taller t1 = new Taller(nombreTaller, direccionTaller, telefonoTaller, latitudTaller, longitudTaller);
				talleres.add(t1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return talleres;

	}

	/**
	 * Funcion que nos devuelve todos los talleres desde hasta la posicion
	 * 
	 * @return
	 */
	public static ArrayList<Taller> getTalleresArr(int ini, int fin) {
		String query = "Select * from taller LIMIT" + ini + " , " + fin;
		Connection conn = (Connection) dbConexion.getConnection();
		ArrayList<Taller> talleres = new ArrayList<>();
		int colcount = 0;
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				String nombreTaller = rs.getString("nombre");
				String direccionTaller = rs.getString("direccion");
				String telefonoTaller = rs.getString("telefono");
				float latitudTaller = rs.getFloat("latitud");
				float longitudTaller = rs.getFloat("longitud");
				Taller t1 = new Taller(nombreTaller, direccionTaller, telefonoTaller, latitudTaller, longitudTaller);
				talleres.add(t1);
			}
			colcount = rs.getMetaData().getColumnCount();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Object[][] multi = new Object[fin - ini][colcount];

		/*
		 * for (int i = 0; i < fin-ini; i++) { for (int j = 0; j < colcount; j++) {
		 * multi[i]=talleres.get(i).getNombre(); } }
		 */

		return talleres;

	}

	public static Object[][] getTalleres(int ini, int fin) {
		// Sentencia
		String query = "Select * from taller LIMIT " + ini + " , " + fin;
		// COnexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Taller> talleres = new ArrayList<Taller>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				Taller t1 = new Taller(rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"),
						rs.getFloat("latitud"), rs.getFloat("longitud"));
				talleres.add(t1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Object[][] datosTaller = new Object[fin - ini][6];
		for (int i = 0; i < talleres.size(); i++) {
			datosTaller[i][0] = talleres.get(i).getNombre();
			datosTaller[i][1] = talleres.get(i).getDireccion();
			datosTaller[i][2] = talleres.get(i).getTelefono();
			datosTaller[i][3] = talleres.get(i).getLatitud();
			datosTaller[i][4] = talleres.get(i).getLongitud();
			datosTaller[i][5] = new JCheckBox("seleccionar", false);
		}

		return datosTaller;

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

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public static void main(String[] args) {

	}
}
