package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import clases.Cita;
import clases.Cliente;
import clases.Taller;
import clases.Usuario;
import clases.Vehiculo;
import clases.Vehiculo_Tipo;
import database.funciones;

public class EditorController implements ActionListener {

	HashMap<String, Component> componentesEditor = new HashMap<String, Component>();
	Object claseAntigua;

	public EditorController(HashMap<String, Component> componentes, Object claseAntigua) {
		componentesEditor = componentes;
		claseAntigua = claseAntigua;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String accion = arg0.getActionCommand();

		if (accion.equalsIgnoreCase("CancelarEditor")) {
			((JFrame) componentesEditor.get("frame")).dispose();
		}
		if (accion.equalsIgnoreCase("InsertarEditor")) {
			if (claseAntigua instanceof Taller) {
				Taller taller = new Taller();
				taller.setNombre(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(0))).getText());
				taller.setDireccion(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(1))).getText());
				taller.setTelefono(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(2))).getText());

				((Taller) claseAntigua).modificar(taller);
			}
			if (claseAntigua instanceof Cita) {
				Cita cita = new Cita();
				// cita.setFecha(fehca);
				// cita.setHora(hora);
				cita.setKm(Integer.parseInt((((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(2))).getText())));
				cita.setId_vehiculo(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(3))).getText());
				cita.setId_taller(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(4))).getText());
				((Cita) claseAntigua).modificar(cita);
			}
			if (claseAntigua instanceof Cliente) {
				Cliente cliente = new Cliente();
				cliente.setDni(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(0))).getText());
				cliente.setNombre(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(1))).getText());
				cliente.setApellidos(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(2))).getText());
				cliente.setTelefono(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(3))).getText());
				cliente.setDireccion(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(4))).getText());
				cliente.setId_usuario(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(5))).getText());
				((Cliente) claseAntigua).modificar(cliente);

			}
			if (claseAntigua instanceof Usuario) {
				Usuario usuario = new Usuario();

				usuario.setUsuario(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(0))).getText());
				usuario.setClave(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(1))).getText());
				((Usuario) claseAntigua).modificar(usuario);

			}
			if (claseAntigua instanceof Vehiculo_Tipo) {

				Vehiculo_Tipo vehiculo_Tipo = new Vehiculo_Tipo();

				vehiculo_Tipo.setId(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(0))).getText());
				vehiculo_Tipo.setDescripcion(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(1))).getText());
				((Vehiculo_Tipo) claseAntigua).modificar(vehiculo_Tipo);
			}
			if (claseAntigua instanceof Vehiculo) {
				Vehiculo vehiculo = new Vehiculo();
				vehiculo.setMatricula(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(0))).getText());
				vehiculo.setMarca(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(1))).getText());
				vehiculo.setModelo(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(2))).getText());
				vehiculo.setAnno(Integer.parseInt(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(3))).getText()));
				vehiculo.setColor(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(4))).getText());
				vehiculo.setId_cliente(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(5))).getText());
				vehiculo.setId_vehiculo_tipo(((JFormattedTextField) componentesEditor
						.get("txtEditor" + funciones.getMetadatosTablaArray().get(6))).getText());
				((Vehiculo) claseAntigua).modificar(vehiculo);

			}
		}
		((JFrame) componentesEditor.get("frame")).dispose();

	}

}
