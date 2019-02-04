package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import interfaz.Exportador;
import interfaz.Importador;

public class PrincipalController implements ActionListener, KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String accion = arg0.getActionCommand();

		if (accion.equalsIgnoreCase("crear")) {
			System.out.println("crear");
		}
		if (accion.equalsIgnoreCase("Borrar")) {
			System.out.println("borrar");
		}
		if (accion.equalsIgnoreCase("editar")) {
			System.out.println("editar");
		}
		if (accion.equalsIgnoreCase("logout")) {
			System.out.println("logout");

		}
		if (accion.equalsIgnoreCase("importar")) {

			Importador importador = new Importador();
		}
		if (accion.equalsIgnoreCase("exportar")) {
			Exportador exportador = new Exportador();
		}

	}

}
