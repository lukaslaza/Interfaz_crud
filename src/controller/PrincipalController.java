package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import clases.Cliente;
import clases.Taller;
import interfaz.Creador;
import interfaz.Exportador;
import interfaz.Importador;
import interfaz.Login;
import interfaz.Principal;

public class PrincipalController implements ActionListener, KeyListener, ChangeListener, TableModelListener {

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
	public void tableChanged(TableModelEvent evento) {
		ArrayList<Object> valor = new ArrayList<Object>();

		// table.getModel().getValueAt(e.getLastRow(),e.getColumn());
		if (evento.getColumn() == database.funciones.getMetadatosTablaArray().size()) {
			System.out
					.println("Row : " + evento.getFirstRow() + " value :" + ((JTable) componentesPrincipal.get("table"))
							.getValueAt(evento.getFirstRow(), evento.getColumn()));
			if (((JTable) componentesPrincipal.get("table")).getValueAt(evento.getFirstRow(), evento.getColumn())
					.equals(true)) {
				valor.add(evento.getFirstRow());
			} else {
				valor.remove(evento.getFirstRow());
			}
		

			for (int i = 0; i < valor.size(); i++) {
				System.out.println(valor);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String accion = arg0.getActionCommand();


		if (accion.equalsIgnoreCase("crear")) {
			new Creador();
		}
		if (accion.equalsIgnoreCase("Borrar")) {
			System.out.println("borrar");
		}
		if (accion.equalsIgnoreCase("editar")) {
			System.out.println("editar");
		}
		if (accion.equalsIgnoreCase("logout")) {
			logout(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("importar")) {
			Importador.getInstance();
		}
		if (accion.equalsIgnoreCase("exportar")) {
			Exportador.getInstance();
		}

		if (accion.equalsIgnoreCase("citas")) {
			Principal.setClaseActual("cita");
			Principal.listarDatos(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("clientes")) {
			Principal.setClaseActual("cliente");
			Principal.listarDatos(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("vehiculos")) {
			Principal.setClaseActual("vehiculo");
			Principal.listarDatos(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("talleres")) {
			Principal.setClaseActual("taller");
			Principal.listarDatos(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("vehiculos_tipos")) {
			Principal.setClaseActual("vehiculo_tipo");
			Principal.listarDatos(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("usuarios")) {
			Principal.setClaseActual("usuario");
			Principal.listarDatos(this, componentesPrincipal);
		}

		if (accion.equalsIgnoreCase("filtrosBuscar")) {
			database.funciones.leerFiltros(this, componentesPrincipal);
		}

		if (accion.equalsIgnoreCase("borrarFiltros")) {
			database.funciones.vaciarFiltros(this, componentesPrincipal);
			System.out.println("borrar");
		}

		// TODO LANZAR MENSAJE DE ALERTA SI NO SE PUEDEN AVANZAR A MAS PAGINAS
		// Paginador
		if (accion.equalsIgnoreCase("paginaSiguente")) {
			if (!(Principal.getPagina() >= Principal.getTotalRegistros() / Principal.getColumnasPagina())) {
				Principal.setPagina(Principal.getPagina() + 1);
				((JSpinner) componentesPrincipal.get("paginaSpinner")).setValue(Principal.getPagina());
				Principal.listarDatos(this, componentesPrincipal);
			}
		}
		if (accion.equalsIgnoreCase("ultimaPagina")) {
			if (!(Principal.getPagina() >= Principal.getTotalRegistros() / Principal.getColumnasPagina())) {
				Principal.setPagina(Principal.getTotalRegistros() / Principal.getColumnasPagina());
				((JSpinner) componentesPrincipal.get("paginaSpinner")).setValue(Principal.getPagina());
				Principal.listarDatos(this, componentesPrincipal);
			}
		}
		if (accion.equalsIgnoreCase("primeraPagina")) {

			if (!(Principal.getPagina() <= 0
					&& Principal.getPagina() >= Principal.getTotalRegistros() / Principal.getColumnasPagina())) {
				Principal.setPagina(1);
				((JSpinner) componentesPrincipal.get("paginaSpinner")).setValue(Principal.getPagina());
				Principal.listarDatos(this, componentesPrincipal);
			}
		}

		if (accion.equalsIgnoreCase("paginaAnterior")) {
			if (!(Principal.getPagina() <= 0)) {
				Principal.setPagina(Principal.getPagina() - 1);
				((JSpinner) componentesPrincipal.get("paginaSpinner")).setValue(Principal.getPagina());
				Principal.listarDatos(this, componentesPrincipal);
			}

		}

		System.out.println("Pagina Actual:" + Principal.getPagina());
		System.out.println("Total Registros: " + Principal.getTotalRegistros());

	}

	public static void logout(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		System.out.println("LOGOUT Efectuado");
		((JFrame) componentesPrincipal.get("framePrincipal")).dispose();
		new Login();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner s = (JSpinner) e.getSource();

		if (((JSpinner) componentesPrincipal.get("paginaSpinner")).getValue() == s.getValue()) {
			Principal.setPagina((int) s.getValue());
		}
		if (((JSpinner) componentesPrincipal.get("paginadorSpinner")).getValue() == s.getValue()) {
			Principal.setColumnasPagina((int) s.getValue());
		}

		Principal.listarDatos(this, componentesPrincipal);
		System.out.println("Pagina Actual:" + Principal.getPagina());
		System.out.println("Total Registros: " + Principal.getTotalRegistros());

		// TODO LISTAR DATOS DE LA MANERA CORRESPONDIENTE

	}

}
