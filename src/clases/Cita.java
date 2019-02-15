package clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

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
}
