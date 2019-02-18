package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;

public class EditorController implements ActionListener {

	HashMap<String, Component> componentesEditor = new HashMap<String, Component>();

	public EditorController(HashMap<String, Component> componentes) {
		componentesEditor = componentes;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String accion = arg0.getActionCommand();

		if (accion.equalsIgnoreCase("CancelarEditor")) {
			System.out.println("hola");
			((JFrame) componentesEditor.get("frame")).dispose();
		}
		if (accion.equalsIgnoreCase("InsertarEditor")) {
			//insertarQuery(getDatos(this, componentesEditor));
		}
		
	}

}
