package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import clases.Cliente;
import clases.Taller;
import interfaz.Exportador;
import interfaz.Importador;
import interfaz.Login;
import interfaz.Principal;

public class PrincipalController implements ActionListener, KeyListener, ChangeListener {

	HashMap<String, Component> componentesPrincipal = new HashMap<String, Component>();

	// Constructor
	public PrincipalController(HashMap<String, Component> componentes) {
		this.componentesPrincipal = componentes;
	}

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
			logout();
		}
		if (accion.equalsIgnoreCase("importar")) {
			Importador importador = new Importador();
		}
		if (accion.equalsIgnoreCase("exportar")) {
			Exportador exportador = new Exportador();
		}

		if (accion.equalsIgnoreCase("citas")) {
			Principal.setClaseActual("cita");
			Principal.listarDatos(componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("clientes")) {
			Principal.setClaseActual("cliente");
			Principal.listarDatos(componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("vehiculos")) {
			Principal.setClaseActual("vehiculo");
			Principal.listarDatos(componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("talleres")) {
			Principal.setClaseActual("taller");
			Principal.listarDatos(componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("vehiculos_tipos")) {
			Principal.setClaseActual("vehiculo_tipo");
			Principal.listarDatos(componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("usuarios")) {
			Principal.setClaseActual("usuario");
			Principal.listarDatos(componentesPrincipal);
		}
		
		if (accion.equalsIgnoreCase("filtrosBuscar")) {
			
		}
		
		if (accion.equalsIgnoreCase("borrarFiltros")) {
			Principal.vaciarFiltros(componentesPrincipal);
		}

	}

	public void logout() {
		Principal.logout((JFrame) componentesPrincipal.get("frame"));
		new Login();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

}
