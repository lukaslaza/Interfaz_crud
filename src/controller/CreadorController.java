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
			crearClase(this, componentesCreador);
		}
	}

	public void crearClase(CreadorController controladorCreador, HashMap<String, Component> componentesCreador) {
		for (int i = 0; i < funciones.getMetadatosTablaArray().size() - 1; i++) {
			switch (Principal.getClaseActual().toLowerCase()) {
			case "cita":
				Cita cita = new Cita();
				/*
				 * cita.setFecha(fecha); cita.setHora(hora); cita.setKm(
				 * Integer.parseInt((((JFormattedTextField)componentesCreador.get("txtCreador" +
				 * funciones.getMetadatosTablaArray().get(i))).getText())));
				 * cita.setId_vehiculo(id_vehiculo); cita.setId_taller(id_taller);
				 */

				break;
			case "taller":
				Taller taller = new Taller();
				/*
				 * taller.setNombre(); taller.setDireccion(); taller.setTelefono();
				 * taller.setLatitud(); taller.setLongitud();
				 */
				break;
			case "vehiculo":
				Vehiculo vehiculo = new Vehiculo();
				/*
				 * vehiculo.setMatricula(matricula); vehiculo.setMarca(marca);
				 * vehiculo.setModelo(modelo); vehiculo.setAnno(anno); vehiculo.setColor(color);
				 * vehiculo.setId_cliente(vehiculo);
				 * vehiculo.setId_vehiculo_tipo(id_vehiculo_tipo);
				 */
				break;
			case "vehiculo_tipo":
				Vehiculo_Tipo vehiculo_Tipo = new Vehiculo_Tipo();
				/*
				 * vehiculo_Tipo.setId(id); vehiculo_Tipo.setDescripcion(descripcion);
				 */

				break;
			case "cliente":
				Cliente cliente = new Cliente();
				/*
				 * cliente.setDni(dni); cliente.setNombre(nombre);
				 * cliente.setApellidos(apellidos); cliente.setTelefono(telefono);
				 * cliente.setDireccion(direccion); cliente.setId_usuario(id_usuario);
				 */
				break;
			case "usuario":
				Usuario usuario = new Usuario();
				/*usuario.setUsuario(usuario);
				usuario.setClave(clave);*/

				break;

			default:
				System.out.println("Tienes que actualizar el metodo de CrearClase()");
				break;
			}
		}

	}
}
