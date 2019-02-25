package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Vehiculo {
	// VALORES
	private String matricula, marca, modelo, color, id_cliente, id_vehiculo_tipo;
	private int anno;

	// CONSTRUCTORES
	public Vehiculo(String matricula, String marca, String modelo, int anno, String color, String id_cliente,
			String id_vehiculo_tipo) {
		setMatricula(matricula);
		setMarca(marca);
		setModelo(modelo);
		setAnno(anno);
		setColor(color);
		setId_cliente(id_cliente);
		setId_vehiculo_tipo(id_vehiculo_tipo);
	}

	public Vehiculo() {
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getId_vehiculo_tipo() {
		return id_vehiculo_tipo;
	}

	public void setId_vehiculo_tipo(String id_vehiculo_tipo) {
		this.id_vehiculo_tipo = id_vehiculo_tipo;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public void insertarse() {
		String query = "INSERT INTO vehiculo (matricula, marca, modelo, anno, color, id_cliente, id_vehiculo_tipo) VALUES(?,?,?,?,?,?,?)";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, getMatricula());
			stmt.setString(2, getMarca());
			stmt.setString(3, getModelo());
			stmt.setInt(4, getAnno());
			stmt.setString(5, getColor());
			stmt.setString(6, getId_cliente());
			stmt.setString(7, getId_vehiculo_tipo());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar el Vehiculo");
		}
	}

	public void modificar(Vehiculo vehiculo) {
		String query = "UPDATE vehiculo SET marca = '?', modelo = '?', anno = '?', "
				+ "color = '?', id_cliente = '?', id_vehiculo_tipo = '?' WHERE matricula like '?'";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, getMarca());
			stmt.setString(2, getModelo());
			stmt.setInt(3, getAnno());
			stmt.setString(4, getColor());
			stmt.setString(5, getId_cliente());
			stmt.setString(6, getId_vehiculo_tipo());
			stmt.setString(7, getMatricula());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al insertar el Vehiculo");
		}
	}

	public void borrarse() {
		String query = " Delete from vehiculo where matricula like '?' ";
		Connection conn = (Connection) dbConexion.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, getMatricula());
			stmt.executeUpdate();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
			System.out.println("Error al borrar el Vehiculo");
		}
	}

}
