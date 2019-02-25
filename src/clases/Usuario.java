package clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

	public Usuario() {
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

	public void insertarse() {
		String query = "INSERT INTO ususario (usuario, clave, token, fecha_inicio, fecha_fin) VALUES(?,?,?,?,?)";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, getUsuario());
			stmt.setString(2, getClave());
			stmt.setString(3, getToken());
			stmt.setDate(4, getFecha_inicio());
			stmt.setDate(5, getFecha_fin());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar el Usuario");
		}
	}

	public static void modificar(Usuario usuario) {
		String query = "UPDATE usuario SET clave = '?', token = '?', fecha_inicio = '?', fecha_fin = '?' "
				+ " WHERE usuario like '?' ";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, usuario.getClave());
			stmt.setString(2, usuario.getToken());
			stmt.setDate(3, usuario.getFecha_inicio() );
			stmt.setDate(4, usuario.getFecha_fin());	
			stmt.setString(5, usuario.getUsuario());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar la Usuario");
		}
	}

	public void borrarse() {
		String query = " Delete from usuario wehere usuario like '"+getUsuario()+"' ";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al borrar la Usuario");
		}
	}
}
