package clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario {

	// VALORES DE LA CLASE
	private String usuario, clave, token;
	private Date fecha_inicio, fecha_fin;

	// CONSTRUCTORES

	// TODOS LOS VALORES
	public Usuario(String usuario, String clave, String token, Date fecha_inicio, Date fecha_fin) {
		setUsuario(usuario);
		setClave(clave);
		setToken(clave);
		setFecha_inicio(fecha_inicio);
		setFecha_fin(fecha_fin);
	}

	// DATOS HABITUALES
	public Usuario(String usuario, String clave, Date fecha_inicio, Date fecha_fin) {
		setUsuario(usuario);
		setClave(clave);
		setFecha_inicio(fecha_inicio);
		setFecha_fin(fecha_fin);
	}

	// SOLO CON LOS VALORES NO NULOS
	public Usuario(String usuario, String clave) {
		setUsuario(usuario);
		setClave(clave);
	}

	// SET
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	// GET
	public String getUsuario() {
		return usuario;
	}

	public String getClave() {
		return clave;
	}

	public String getToken() {
		return token;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	// DB
	/**
	 * Funcion que nos devulve los nombre de las cabeceras de la tabla Usuario
	 * 
	 * @return String[] con las cabeceras
	 */
	public static String[] getUsuarioColumnNames() {

		String query = "Select * from usuario";
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

	// POSIBLE ERROR CON LOS VALORES VACIOS
	/**
	 * Devuelve Todos los usuarios
	 * 
	 * @return Object[][] de citas
	 */
	public static Object[][] getUsuarios() {
		// Sentencia
		// String query = "Select * from taller LIMIT " + ini + " , " + fin;
		String query = "Select * from usuario ";
		// Conexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				Usuario u1 = new Usuario(rs.getString("usuario"), rs.getString("clave"));
				if (rs.getString("token") != null) {
					u1.setToken(rs.getString("token"));
				}
				if (rs.getDate("fecha_inicio") != null) {
					u1.setFecha_inicio(rs.getDate("fecha_inicio"));
				}
				if (rs.getDate("fecha_fin") != null) {
					u1.setFecha_fin(rs.getDate("fecha_fin"));
				}
				usuarios.add(u1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Object[][] datosUsuario = new Object[usuarios.size()][getUsuarioColumnNames().length];
		for (int i = 0; i < usuarios.size(); i++) {

			datosUsuario[i][0] = usuarios.get(i).getUsuario();
			datosUsuario[i][1] = usuarios.get(i).getClave();

			if (usuarios.get(i).getToken() == null || usuarios.get(i).getToken() == " ") {
				// datosUsuario[i][2] = " ";
			} else {
				datosUsuario[i][2] = usuarios.get(i).getToken();
			}

			if (usuarios.get(i).getFecha_inicio() == null) {
				// datosUsuario[i][3] = null;
			} else {
				datosUsuario[i][3] = usuarios.get(i).getFecha_inicio();
			}
			if (usuarios.get(i).getFecha_fin() == null) {
				// datosUsuario[i][4] = null;
			} else {
				datosUsuario[i][4] = usuarios.get(i).getFecha_fin();
			}
			datosUsuario[i][5] = new Boolean(false);
		}

		return datosUsuario;

	}
}
