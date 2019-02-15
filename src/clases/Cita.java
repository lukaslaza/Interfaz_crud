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

	// DB
	/**
	 * Funcion que nos devulve los nombre de las cabeceras de la tabla cita
	 * 
	 * @return String[] con las cabeceras
	 */
	public static String[] getCitaColumnNames() {

		String query = "Select * from cita";
		Connection conn = (Connection) dbConexion.getConnection();
		ArrayList<String> metadatos = new ArrayList<String>();
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			// EJECUTAMOS LA SENTENCIA
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

	//TODO Ver problema de formato con la hora
	/**
	 * Devuelve Todas las citas
	 * @return Object[][] de citas
	 */
	public static Object[][] getCitas() {
		// Sentencia
		// String query = "Select * from taller LIMIT " + ini + " , " + fin;
		String query = "Select * from cita ";
		// Conexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Cita> citas = new ArrayList<Cita>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				citas.add(new Cita(rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("km"),
						rs.getString("id_vehiculo"), rs.getString("id_taller")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Object[][] datosCitas = new Object[citas.size()][getCitaColumnNames().length];
		for (int i = 0; i < citas.size(); i++) {
			datosCitas[i][0] = citas.get(i).getFecha();
			datosCitas[i][1] = citas.get(i).getHora();
			datosCitas[i][2] = citas.get(i).getKm();
			datosCitas[i][3] = citas.get(i).getId_vehiculo();
			datosCitas[i][4] = citas.get(i).getId_taller();
			datosCitas[i][5] = new Boolean(false);
		}

		return datosCitas;

	}
}
