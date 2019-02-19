package controller;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clases.dbConexion;
import database.funciones;
import funciones.funcionesError;
import interfaz.Creador;
import interfaz.Principal;

public class CreadorController implements ActionListener {

	HashMap<String, Component> componentesCreador = new HashMap<String, Component>();

	public CreadorController(HashMap<String, Component> componentes) {
		componentesCreador = componentes;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String accion = arg0.getActionCommand();

		if (accion.equalsIgnoreCase("CancelarCreador")) {
			System.out.println("hola");
			((JFrame) componentesCreador.get("frame")).dispose();
		}
		if (accion.equalsIgnoreCase("InsertarCreador")) {
			insertarQuery(getDatos(this, componentesCreador));
		}
	}

	// TODO POSIBLE PROBLEMA CON EL TIPO DE DATOS
	// TODO COMPROBAR QUE LOS CAMPOS MAS IMPORTANTES NO ESTEN VACIOS HACIENDO USO
	// DEL CONSTRUCTOR DE LA CLASE CORRESPONDIENTE
	// SOLTAR ERROR SI ES ASI
	public String getDatos(CreadorController controladorPrincipal, HashMap<String, Component> componentesPrincipal) {
		
		/*INSERT INTO Customer (FirstName, LastName, City, Country, Phone)
		VALUES ('Craig', 'Smith', 'New York', 'USA', 1-01-993 2800)*/
		
		String sentencia = "INSERT INTO " + Principal.getClaseActual()+" ";
		for (int i = 0; i < funciones.getMetadatosTablaArray().size() - 1; i++) {
				if ( ((JTextField) componentesCreador.get("txtCreador" + funciones.getMetadatosTablaArray().get(i))) instanceof JTextField  ){
					if (componentesCreador.get("txtCreador" + funciones.getMetadatosTablaArray().get(i)) ==) {
						
					}
				}
		}
		
		
		 sentencia = "INSERT INTO " + Principal.getClaseActual() + " values (";

		for (int i = 0; i < funciones.getMetadatosTablaArray().size() - 1; i++) {
			if (funciones.getMetadatosTablaArrayType().get(i) == 1 || funciones.getMetadatosTablaArrayType().get(i) == 12) {
				if (componentesCreador
						.get("txtCreador" + funciones.getMetadatosTablaArray().get(i)) instanceof JTextField) {
					sentencia = sentencia + " \"";
					sentencia = sentencia + ((JTextField) componentesCreador
							.get("txtCreador" + funciones.getMetadatosTablaArray().get(i))).getText().trim();
					if (funciones.getMetadatosTablaArray().size() - 1 == i + 1) {
						sentencia = sentencia + "\"";
					} else {
						sentencia = sentencia + "\",";
					}
				}
			}else {
				if (componentesCreador
						.get("txtCreador" + funciones.getMetadatosTablaArray().get(i)) instanceof JTextField) {
					sentencia = sentencia + " ";
					sentencia = sentencia + ((JTextField) componentesCreador
							.get("txtCreador" + funciones.getMetadatosTablaArray().get(i))).getText().trim();
					if (funciones.getMetadatosTablaArray().size() - 1 == i + 1) {
						sentencia = sentencia + "";
					} else {
						sentencia = sentencia + ",";
					}
				}
			}

		}
		sentencia = sentencia + ");";
System.out.println(sentencia);
		return sentencia;
	}

	public void insertarQuery(String query) {

		Connection conn = (Connection) dbConexion.getConnection();
		ArrayList<String> metadatos = new ArrayList<String>();
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		// EJECUTAMLS SENTENCIA
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Ha habido un error");
			e.printStackTrace();
			funcionesError.error_msg((Frame) componentesCreador.get("frame"),
					e.getMessage(), "Error de inserción");
		}
		
		
	}
}
