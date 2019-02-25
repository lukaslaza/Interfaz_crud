package clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import funciones.funcionesError;
import interfaz.Principal;

public class Cita {

	// VALORES DE LA CLASE
	private Date fecha;
	private String id_vehiculo, id_taller;
	private Time hora;
	private int km;

	// CONSTRUCTORES
	public Cita(Date fecha, Time hora, int km, String id_vehiculo, String id_taller) {
		setFecha(fecha);
		setHora(hora);
		setKm(km);
		setId_vehiculo(id_vehiculo);
		setId_taller(id_taller);
	}

	public Cita() {
	}

	// GET
	public Date getFecha() {
		return fecha;
	}

	public String getId_vehiculo() {
		return id_vehiculo;
	}

	public String getId_taller() {
		return id_taller;
	}

	public Time getHora() {
		return hora;
	}

	public int getKm() {
		return km;
	}

	// SET
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setId_vehiculo(String id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}

	public void setId_taller(String id_taller) {
		this.id_taller = id_taller;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public void insertarse() {
		String query = "INSERT INTO cita(fecha, hora, km, id_vehiculo, id_taller) VALUES(?,?,?,?,?)";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDate(1, getFecha());
			stmt.setTime(2, getHora());
			stmt.setInt(3, getKm());
			stmt.setString(4, getId_vehiculo());
			stmt.setString(5, getId_taller());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar Cita");
		}
	}

	public static void modificar(Cita cita) {
		String query = "UPDATE cita SET hora = '?', km = '?', id_taller = '?', "
				+ " WHERE fecha like '?' and id_vehiculo like '?' ";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setTime(1, cita.getHora());
			stmt.setInt(2, cita.getKm());
			stmt.setString(3, cita.getId_taller());
			stmt.setDate(4, cita.getFecha());
			stmt.setString(5, cita.getId_vehiculo());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar la Cita");
		}
	}

	public void borrarse() {
		String query = " Delete from cita wehere fecha = '" + getFecha() + "' and id_vehiculo like '" + getId_vehiculo()
				+ "' ";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al borrar la Cita");
		}
	}
}
