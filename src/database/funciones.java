package database;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Cita;
import clases.Cliente;
import clases.Taller;
import clases.Usuario;
import clases.Vehiculo_Tipo;
import clases.Vehiculo;
import clases.dbConexion;
import controller.PrincipalController;
import interfaz.Principal;
import interfaz.TablaListar;

public class funciones {

	/**
	 * La posicion del primer elemento de la pagina en funcion de su pagina. Por
	 * ejemplo, de una pagina de 20 elementos, para la pagina 2 el primer elemento
	 * que necesitamos es el 41. A partir del 41, recogemos 20 elementos y ya
	 * tenemos nuestra pagina. Para ello podemos utilizar este metodo
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static int paginaActual(int currentPage, int pageSize) {
		return Math.max((pageSize * (currentPage - 1)) + 1, 1);
	}

	public static int paginasMaximas(int registrosTotales, int registrosPorPagina) {
		return (int) Math.ceil(registrosTotales / registrosPorPagina);
	}

	// METADATOS
	public static ArrayList<String> getMetadatosTablaArray() {

		String query = "Select * from " + Principal.getClaseActual();
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

		return metadatos;
	}

	public static String[] getMetadatosTabla() {

		String query = "Select * from " + Principal.getClaseActual();
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

	public static ArrayList<Object> getDatosArray(int paginaActual, int registrosPagina) {
		// Sentencia
		String query = "SELECT * FROM " + Principal.getClaseActual() + " LIMIT " + registrosPagina * paginaActual
				+ " , " + (registrosPagina * paginaActual) + registrosPagina + ";";
		// COnexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Object> clases = new ArrayList<Object>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				switch (Principal.getClaseActual()) {
				case "taller":
					clases.add(new Taller(rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"),
							rs.getFloat("latitud"), rs.getFloat("longitud")));
					break;
				case "cliente":
					clases.add(new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
							rs.getString("telefono"), rs.getString("direccion"), rs.getString("id_usuario")));
					break;
				case "cita":
					clases.add(new Cita(rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("km"),
							rs.getString("id_vehiculo"), rs.getString("id_taller")));
					break;
				case "vehiculo":
					clases.add(new Vehiculo(rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"),
							rs.getInt("anno"), rs.getString("color"), rs.getString("id_cliente"),
							rs.getString("id_vehiculo_tipo")));
					break;
				case "vehiculo_tipo":
					clases.add(new Vehiculo_Tipo(rs.getString("id"), rs.getString("descripcion")));
					break;
				case "usuario":
					clases.add(new Usuario(rs.getString("usuario"), rs.getString("clave"), rs.getString("token"),
							rs.getDate("fecha_inicio"), rs.getDate("fecha_fin")));
					break;

				default:
					System.out.println("DEBES ACTUALIZAR ESTE METODO --> getDatos()");
					break;
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Principal.setTotalRegistros(clases.size());

		return clases;

	}
	
	public static ArrayList<Object> getDatosArray(String query, int paginaActual, int registrosPagina) {
		// Sentencia
		/*String query = "SELECT * FROM " + Principal.getClaseActual() + " LIMIT " + registrosPagina * paginaActual
				+ " , " + (registrosPagina * paginaActual) + registrosPagina + ";";*/
		// COnexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Object> clases = new ArrayList<Object>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				switch (Principal.getClaseActual()) {
				case "taller":
					clases.add(new Taller(rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"),
							rs.getFloat("latitud"), rs.getFloat("longitud")));
					break;
				case "cliente":
					clases.add(new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
							rs.getString("telefono"), rs.getString("direccion"), rs.getString("id_usuario")));
					break;
				case "cita":
					clases.add(new Cita(rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("km"),
							rs.getString("id_vehiculo"), rs.getString("id_taller")));
					break;
				case "vehiculo":
					clases.add(new Vehiculo(rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"),
							rs.getInt("anno"), rs.getString("color"), rs.getString("id_cliente"),
							rs.getString("id_vehiculo_tipo")));
					break;
				case "vehiculo_tipo":
					clases.add(new Vehiculo_Tipo(rs.getString("id"), rs.getString("descripcion")));
					break;
				case "usuario":
					clases.add(new Usuario(rs.getString("usuario"), rs.getString("clave"), rs.getString("token"),
							rs.getDate("fecha_inicio"), rs.getDate("fecha_fin")));
					break;

				default:
					System.out.println("DEBES ACTUALIZAR ESTE METODO --> getDatos()");
					break;
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Principal.setTotalRegistros(clases.size());

		return clases;

	}

	public static Object[][] getDatos(int paginaActual, int registrosPagina) {
		// Sentencia
		String query = "SELECT * FROM " + Principal.getClaseActual() + " LIMIT " + registrosPagina * paginaActual
				+ " , " + (registrosPagina * paginaActual) + registrosPagina + ";";
		// COnexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Object> clases = new ArrayList<Object>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				switch (Principal.getClaseActual()) {
				case "taller":
					clases.add(new Taller(rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"),
							rs.getFloat("latitud"), rs.getFloat("longitud")));
					break;
				case "cliente":
					clases.add(new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
							rs.getString("telefono"), rs.getString("direccion"), rs.getString("id_usuario")));
					break;
				case "cita":
					clases.add(new Cita(rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("km"),
							rs.getString("id_vehiculo"), rs.getString("id_taller")));
					break;
				case "vehiculo":
					clases.add(new Vehiculo(rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"),
							rs.getInt("anno"), rs.getString("color"), rs.getString("id_cliente"),
							rs.getString("id_vehiculo_tipo")));
					break;
				case "vehiculo_tipo":
					clases.add(new Vehiculo_Tipo(rs.getString("id"), rs.getString("descripcion")));
					break;
				case "usuario":
					clases.add(new Usuario(rs.getString("usuario"), rs.getString("clave"), rs.getString("token"),
							rs.getDate("fecha_inicio"), rs.getDate("fecha_fin")));
					break;

				default:
					System.out.println("DEBES ACTUALIZAR ESTE METODO --> getDatos()");
					break;
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Object[][] datos = new Object[clases.size()][getMetadatosTablaArray().size()];

		ArrayList<Taller> talleres = new ArrayList<Taller>();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ArrayList<Cita> citas = new ArrayList<Cita>();
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		ArrayList<Vehiculo_Tipo> vehiculo_tipos = new ArrayList<Vehiculo_Tipo>();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		for (int i = 0; i < clases.size(); i++) {

			if (clases.get(i) instanceof Taller) {
				talleres.add((Taller) clases.get(i));
				datos[i][0] = talleres.get(i).getNombre();
				datos[i][1] = talleres.get(i).getDireccion();
				datos[i][2] = talleres.get(i).getTelefono();
				datos[i][3] = talleres.get(i).getLatitud();
				datos[i][4] = talleres.get(i).getLongitud();
				datos[i][5] = new Boolean(false);
			}
			if (clases.get(i) instanceof Cliente) {
				clientes.add((Cliente) clases.get(i));
				datos[i][0] = clientes.get(i).getDni();
				datos[i][1] = clientes.get(i).getNombre();
				datos[i][2] = clientes.get(i).getApellidos();
				datos[i][3] = clientes.get(i).getTelefono();
				datos[i][4] = clientes.get(i).getDireccion();
				datos[i][5] = clientes.get(i).getId_usuario();
				datos[i][6] = new Boolean(false);
			}
			if (clases.get(i) instanceof Cita) {
				citas.add((Cita) clases.get(i));
				datos[i][0] = citas.get(i).getFecha();
				datos[i][1] = citas.get(i).getHora();
				datos[i][2] = citas.get(i).getKm();
				datos[i][3] = citas.get(i).getId_vehiculo();
				datos[i][4] = citas.get(i).getId_taller();
				datos[i][5] = new Boolean(false);
			}
			if (clases.get(i) instanceof Vehiculo) {
				vehiculos.add((Vehiculo) clases.get(i));
				datos[i][0] = vehiculos.get(i).getMatricula();
				datos[i][1] = vehiculos.get(i).getMarca();
				datos[i][2] = vehiculos.get(i).getModelo();
				datos[i][3] = vehiculos.get(i).getAnno();
				datos[i][4] = vehiculos.get(i).getColor();
				datos[i][5] = vehiculos.get(i).getId_cliente();
				datos[i][6] = vehiculos.get(i).getId_vehiculo_tipo();
				datos[i][7] = new Boolean(false);
			}
			if (clases.get(i) instanceof Vehiculo_Tipo) {
				vehiculo_tipos.add((Vehiculo_Tipo) clases.get(i));
				datos[i][0] = vehiculo_tipos.get(i).getId();
				datos[i][1] = vehiculo_tipos.get(i).getDescripcion();
				datos[i][2] = new Boolean(false);
			}
			if (clases.get(i) instanceof Usuario) {
				usuarios.add((Usuario) clases.get(i));
				datos[i][0] = usuarios.get(i).getUsuario();
				datos[i][1] = usuarios.get(i).getClave();
				datos[i][2] = usuarios.get(i).getToken();
				datos[i][3] = usuarios.get(i).getFecha_inicio();
				datos[i][4] = usuarios.get(i).getFecha_fin();
				datos[i][5] = new Boolean(false);
			}

		}

		Principal.setTotalRegistros(datos.length);
		return datos;
	}

	public static Object[][] getDatos(String query, int paginaActual, int registrosPagina) {
		// Sentencia
		/*
		 * String query = "SELECT * FROM " + Principal.getClaseActual() + " LIMIT " +
		 * registrosPagina * paginaActual + " , " + (registrosPagina * paginaActual) +
		 * registrosPagina + ";";
		 */
		// COnexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Object> clases = new ArrayList<Object>();

		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				switch (Principal.getClaseActual()) {
				case "taller":
					clases.add(new Taller(rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"),
							rs.getFloat("latitud"), rs.getFloat("longitud")));
					break;
				case "cliente":
					clases.add(new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
							rs.getString("telefono"), rs.getString("direccion"), rs.getString("id_usuario")));
					break;
				case "cita":
					clases.add(new Cita(rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("km"),
							rs.getString("id_vehiculo"), rs.getString("id_taller")));
					break;
				case "vehiculo":
					clases.add(new Vehiculo(rs.getString("matricula"), rs.getString("marca"), rs.getString("modelo"),
							rs.getInt("anno"), rs.getString("color"), rs.getString("id_cliente"),
							rs.getString("id_vehiculo_tipo")));
					break;
				case "vehiculo_tipo":
					clases.add(new Vehiculo_Tipo(rs.getString("id"), rs.getString("descripcion")));
					break;
				case "usuario":
					clases.add(new Usuario(rs.getString("usuario"), rs.getString("clave"), rs.getString("token"),
							rs.getDate("fecha_inicio"), rs.getDate("fecha_fin")));
					break;

				default:
					System.out.println("DEBES ACTUALIZAR ESTE METODO --> getDatos()");
					break;
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Object[][] datos = new Object[clases.size()][getMetadatosTablaArray().size()];

		ArrayList<Taller> talleres = new ArrayList<Taller>();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ArrayList<Cita> citas = new ArrayList<Cita>();
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		ArrayList<Vehiculo_Tipo> vehiculo_tipos = new ArrayList<Vehiculo_Tipo>();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		for (int i = 0; i < clases.size(); i++) {

			if (clases.get(i) instanceof Taller) {
				talleres.add((Taller) clases.get(i));
				datos[i][0] = talleres.get(i).getNombre();
				datos[i][1] = talleres.get(i).getDireccion();
				datos[i][2] = talleres.get(i).getTelefono();
				datos[i][3] = talleres.get(i).getLatitud();
				datos[i][4] = talleres.get(i).getLongitud();
				datos[i][5] = new Boolean(false);
			}
			if (clases.get(i) instanceof Cliente) {
				clientes.add((Cliente) clases.get(i));
				datos[i][0] = clientes.get(i).getDni();
				datos[i][1] = clientes.get(i).getNombre();
				datos[i][2] = clientes.get(i).getApellidos();
				datos[i][3] = clientes.get(i).getTelefono();
				datos[i][4] = clientes.get(i).getDireccion();
				datos[i][5] = clientes.get(i).getId_usuario();
				datos[i][6] = new Boolean(false);
			}
			if (clases.get(i) instanceof Cita) {
				citas.add((Cita) clases.get(i));
				datos[i][0] = citas.get(i).getFecha();
				datos[i][1] = citas.get(i).getHora();
				datos[i][2] = citas.get(i).getKm();
				datos[i][3] = citas.get(i).getId_vehiculo();
				datos[i][4] = citas.get(i).getId_taller();
				datos[i][5] = new Boolean(false);
			}
			if (clases.get(i) instanceof Vehiculo) {
				vehiculos.add((Vehiculo) clases.get(i));
				datos[i][0] = vehiculos.get(i).getMatricula();
				datos[i][1] = vehiculos.get(i).getMarca();
				datos[i][2] = vehiculos.get(i).getModelo();
				datos[i][3] = vehiculos.get(i).getAnno();
				datos[i][4] = vehiculos.get(i).getColor();
				datos[i][5] = vehiculos.get(i).getId_cliente();
				datos[i][6] = vehiculos.get(i).getId_vehiculo_tipo();
				datos[i][7] = new Boolean(false);
			}
			if (clases.get(i) instanceof Vehiculo_Tipo) {
				vehiculo_tipos.add((Vehiculo_Tipo) clases.get(i));
				datos[i][0] = vehiculo_tipos.get(i).getId();
				datos[i][1] = vehiculo_tipos.get(i).getDescripcion();
				datos[i][2] = new Boolean(false);
			}
			if (clases.get(i) instanceof Usuario) {
				usuarios.add((Usuario) clases.get(i));
				datos[i][0] = usuarios.get(i).getUsuario();
				datos[i][1] = usuarios.get(i).getClave();
				datos[i][2] = usuarios.get(i).getToken();
				datos[i][3] = usuarios.get(i).getFecha_inicio();
				datos[i][4] = usuarios.get(i).getFecha_fin();
				datos[i][5] = new Boolean(false);
			}

		}

		Principal.setTotalRegistros(datos.length);
		return datos;
	}

	// TODO IDENTIFICAR TIPO DE CAMPO
	/**
	 * GENERA EL PANEL QUE CONTIENE UN GRID LAYOUT CON LOS CAMPOS NECESARIOS PARA
	 * HACER EL FILTRADO
	 * 
	 * @param controladorPrincipal
	 * @param componentesPrincipal
	 * @return PANEL con el filtro ya echo
	 */
	public static JPanel crearFiltros(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {

		// Creamos el panel
		JPanel panel_filtros = new JPanel();

		// ESTABLECEMOS UN LAYAOUT
		panel_filtros.setLayout(new GridLayout(1, getMetadatosTablaArray().size(), 0, 0));

		// CREAMOS LOS COMPONENTES
		for (int i = 0; i < getMetadatosTablaArray().size() - 1; i++) {
			// LABEL
			componentesPrincipal.put(
					"lblFiltro" + Principal.getClaseActual() + "_" + getMetadatosTablaArray().get(i).toLowerCase(),
					new JLabel(getMetadatosTablaArray().get(i) + ": "));
			componentesPrincipal.put(
					"txtFiltro" + Principal.getClaseActual() + "_" + getMetadatosTablaArray().get(i).toLowerCase(),
					new JTextField());

			panel_filtros.add(componentesPrincipal.get(
					"lblFiltro" + Principal.getClaseActual() + "_" + getMetadatosTablaArray().get(i).toLowerCase()));
			panel_filtros.add(componentesPrincipal.get(
					"txtFiltro" + Principal.getClaseActual() + "_" + getMetadatosTablaArray().get(i).toLowerCase()));
		}

		JButton btnFiltrosBuscar = new JButton("Buscar");
		JButton btnFiltrosBorrar = new JButton("Borrar");
		btnFiltrosBuscar.addActionListener(controladorPrincipal);
		btnFiltrosBorrar.addActionListener(controladorPrincipal);
		btnFiltrosBuscar.setActionCommand("filtrosBuscar");
		btnFiltrosBorrar.setActionCommand("borrarFiltros");

		componentesPrincipal.put("btnFiltrosBorrar", btnFiltrosBorrar);
		componentesPrincipal.put("btnFiltrosBuscar", btnFiltrosBuscar);
		panel_filtros.add(btnFiltrosBuscar);
		panel_filtros.add(btnFiltrosBorrar);

		return panel_filtros;

	}

	public static void vaciarFiltros(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		for (int i = 0; i < getMetadatosTablaArray().size() - 1; i++) {
			((JTextField) componentesPrincipal.get(
					"txtFiltro" + Principal.getClaseActual() + "_" + getMetadatosTablaArray().get(i).toLowerCase()))
							.setText("");
		}

	}

	public static void leerFiltros(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		String sentencia = " where true ";
		String clase = "";
		for (int i = 0; i < getMetadatosTablaArray().size() - 1; i++) {
			clase = ("txtFiltro" + Principal.getClaseActual() + "_" + getMetadatosTablaArray().get(i).toLowerCase());
			if (((JTextField) componentesPrincipal.get(clase)).getText() == null
					|| ((JTextField) componentesPrincipal.get(clase)).getText() == ""
					|| ((JTextField) componentesPrincipal.get(clase)).getText() == " "
					|| ((JTextField) componentesPrincipal.get(clase)).getText().isEmpty()) {
			} else {
				sentencia = sentencia + "and " + getMetadatosTablaArray().get(i).toLowerCase() + " like " + "'"
						+ ((JTextField) componentesPrincipal.get(clase)).getText() + "' ";
			}

		}

	}

}
