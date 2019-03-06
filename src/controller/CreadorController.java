package controller;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.bind.ParseConversionEvent;

import org.mariadb.jdbc.internal.com.send.authentication.ClearPasswordPlugin;

import clases.Cita;
import clases.Cliente;
import clases.Taller;
import clases.Usuario;
import clases.Vehiculo;
import clases.Vehiculo_Tipo;
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
			System.out.println("Cerrando Creador de Clases");
			((JFrame) componentesCreador.get("frame")).dispose();
		}
		if (accion.equalsIgnoreCase("InsertarCreador")) {
			try {

				crearClase(this, componentesCreador);

				funcionesError.aviso_msg(((JFrame) componentesCreador.get("frame")), "¡Insertado!",
						"Insercion Correcta");
			} catch (SQLException e) {
				funcionesError.error_msg(((JFrame) componentesCreador.get("frame")), "¡Error al insertar!",
						"Insercion Fallida");
			} finally {
				((JFrame) componentesCreador.get("frame")).dispose();
			}
		}
	}

	// TODO problema con el tipo de datos recogidos.
	public void crearClase(CreadorController controladorCreador, HashMap<String, Component> componentesCreador) throws SQLException{

		switch (Principal.getClaseActual().toLowerCase()) {
		case "cita":
			// ((JFormattedTextField)
			// componentesCreador.get("txtCreador"+funciones.getMetadatosTablaArray().get(i))).getText())
			Cita cita = new Cita();
			// cita.setFecha(fehca);
			// cita.setHora(hora);
			cita.setKm(Integer.parseInt((((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(2))).getText())));
			cita.setId_vehiculo(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(3))).getText());
			cita.setId_taller(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(4))).getText());
			cita.insertarse();
			break;
		case "taller":
			Taller taller = new Taller();
			taller.setNombre(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(0))).getText());
			taller.setDireccion(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(1))).getText());
			taller.setTelefono(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(2))).getText());
			// taller.setLatitud();
			// taller.setLongitud();
			taller.insertarse();
			break;
		case "vehiculo":
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setMatricula(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(0))).getText());
			vehiculo.setMarca(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(1))).getText());
			vehiculo.setModelo(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(2))).getText());
			vehiculo.setAnno(Integer.parseInt(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(3))).getText()));
			vehiculo.setColor(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(4))).getText());
			vehiculo.setId_cliente(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(5))).getText());
			vehiculo.setId_vehiculo_tipo(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(6))).getText());
			vehiculo.insertarse();
			break;
		case "vehiculo_tipo":
			Vehiculo_Tipo vehiculo_Tipo = new Vehiculo_Tipo();

			vehiculo_Tipo.setId(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(0))).getText());
			vehiculo_Tipo.setDescripcion(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(1))).getText());
			vehiculo_Tipo.insertarse();
			break;
		case "cliente":
			Cliente cliente = new Cliente();
			cliente.setDni(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(0))).getText());
			cliente.setNombre(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(1))).getText());
			cliente.setApellidos(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(2))).getText());
			cliente.setTelefono(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(3))).getText());
			cliente.setDireccion(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(4))).getText());
			cliente.setId_usuario(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(5))).getText());
			cliente.insertarse();
			break;
		case "usuario":
			Usuario usuario = new Usuario();

			usuario.setUsuario(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(0))).getText());
			usuario.setClave(((JFormattedTextField) componentesCreador
					.get("txtCreador" + funciones.getMetadatosTablaArray().get(1))).getText());
			usuario.insertarse();
			break;

		default:
			System.out.println("Tienes que actualizar el metodo de CrearClase()");
			break;

		}

	}
}
