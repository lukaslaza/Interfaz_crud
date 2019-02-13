package controller;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import interfaz.Exportador;
import interfaz.Importador;
import interfaz.Login;
import interfaz.Principal;

public class PrincipalController implements ActionListener, KeyListener, ChangeListener {
	
	HashMap<String, Component> componentesImportador=new HashMap<String, Component>();
	
	// Constructor
	public PrincipalController(HashMap<String, Component> componentes) {
		this.componentesImportador = componentes;
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
			// Cita.listar(0,20);
			System.out.println("citas");
		}
		if (accion.equalsIgnoreCase("clientes")) {
			// Cliente.listar(0,20);
			System.out.println("clientes");
		}
		if (accion.equalsIgnoreCase("vehiculos")) {
			// Vehiculo.listar(0,20);
			System.out.println("vehiculos");
		}
		if (accion.equalsIgnoreCase("talleres")) {
			// Taller.listar(0,20);
			System.out.println("talleres");
		}
		if (accion.equalsIgnoreCase("vehiculos_tipos")) {
			// VehiculoTipo.listar(0,20);
			System.out.println("vehiculos_tipos");
		}
		if (accion.equalsIgnoreCase("usuarios")) {
			// Usuario.listar(0,20);
			System.out.println("usuarios");
		}

	}

	public void logout() {
		Principal.logout((JFrame) componentesImportador.get("frame"));
		new Login();
	}



	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
