package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clases.dbConexion;
import database.funciones;
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
		String sentencia = "Insert into " + Principal.getClaseActual() + " values (";

		for (int i = 0; i < funciones.getMetadatosTablaArray().size() - 1; i++) {
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

		}
		sentencia = sentencia + ");";

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
			funciones.funcionesError.msgError();
		}
	}
}
