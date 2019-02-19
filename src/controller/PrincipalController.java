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
		int totalcolumnas = database.funciones.getMetadatosTablaArray().size();
		if (evento.getColumn() == (totalcolumnas - 1)) {
			System.out
					.println("Row : " + evento.getFirstRow() + " value :" + ((JTable) componentesPrincipal.get("table"))
							.getValueAt(evento.getFirstRow(), evento.getColumn()));
			if (((JTable) componentesPrincipal.get("table")).getValueAt(evento.getFirstRow(), evento.getColumn())
					.equals(true)) {
				valor.add(evento.getFirstRow());
			} else {
				valor.remove(evento);
			}

			for (int i = 0; i < valor.size(); i++) {
				System.out.println((TableModelEvent) valor.get(i));
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
			// TODO CONSEGUIR TODOS LOS BOOLEAN TRUE SU PRIMER VALOR QUE DEBERIA SER LA
			// CLAVE Y DELETE FROM DATABASE
		}
		if (accion.equalsIgnoreCase("editar")) {
			// TODO VER QUE SOLO ESTE SELECCIONADO UN VALOR; Y SI PULSAMOS SOBRE EDITAR QUE
			// SE ABRA UN CREADOR CON LOS DATOS YA RELLENADOS Y QUE SE PUEDA EDITAR; UPDATE
			// FROM O DELETE Y UN INSERT
		}
		if (accion.equalsIgnoreCase("logout")) {
			logout(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("importar")) {
			// TODO SOLO CREA LA VENTANA NO HACE NADA
			Importador.getInstance();
		}
		if (accion.equalsIgnoreCase("exportar")) {
			// TODO SOLO CREA LA VENTANA NO HACE NADA
			Exportador.getInstance();
		}

		if (accion.equalsIgnoreCase("citas")) {
			Principal.setClaseActual("cita");
			Principal.CrearListarDatos("", this, componentesPrincipal);
			Principal.CrearFiltros(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("clientes")) {
			Principal.setClaseActual("cliente");
			Principal.CrearListarDatos("", this, componentesPrincipal);
			Principal.CrearFiltros(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("vehiculos")) {
			Principal.setClaseActual("vehiculo");
			Principal.CrearListarDatos("", this, componentesPrincipal);
			Principal.CrearFiltros(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("talleres")) {
			Principal.setClaseActual("taller");
			Principal.CrearListarDatos("", this, componentesPrincipal);
			Principal.CrearFiltros(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("vehiculos_tipos")) {
			Principal.setClaseActual("vehiculo_tipo");
			Principal.CrearListarDatos("", this, componentesPrincipal);
			Principal.CrearFiltros(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("usuarios")) {
			Principal.setClaseActual("usuario");
			Principal.CrearListarDatos("", this, componentesPrincipal);
			Principal.CrearFiltros(this, componentesPrincipal);
		}

		if (accion.equalsIgnoreCase("filtrosBuscar")) {
			database.funciones.leerFiltros(this, componentesPrincipal);
		}

		if (accion.equalsIgnoreCase("borrarFiltros")) {
			database.funciones.vaciarFiltros(this, componentesPrincipal);
		}

		// TODO LANZAR MENSAJE DE ALERTA SI NO SE PUEDEN AVANZAR A MAS PAGINAS
		// Paginador
		if (accion.equalsIgnoreCase("paginaSiguente")) {
			if (!(Principal.getPagina() > Principal.getTotalRegistros() / Principal.getColumnasPagina())) {
				Principal.setPagina(Principal.getPagina() + 1);
				Principal.CrearListarDatos("", this, componentesPrincipal);
			}
		}
		if (accion.equalsIgnoreCase("ultimaPagina")) {
			System.out.println(Principal.getTotalRegistros());
			System.out.println(Principal.getColumnasPagina());
			Principal.setPagina((int) Math.ceil(Principal.getTotalRegistros() / Principal.getColumnasPagina()));
			Principal.CrearListarDatos("", this, componentesPrincipal);

		}
		if (accion.equalsIgnoreCase("primeraPagina")) {
			Principal.setPagina(1);
			Principal.CrearListarDatos("", this, componentesPrincipal);
		}

		if (accion.equalsIgnoreCase("paginaAnterior")) {
			if (!(Principal.getPagina() == 1)) {
				Principal.setPagina(Principal.getPagina() - 1);
				Principal.CrearListarDatos("", this, componentesPrincipal);
			}

		}

		((JSpinner) componentesPrincipal.get("paginaSpinner")).setValue(Principal.getPagina());
		System.out.println("Pagina Actual:" + Principal.getPagina());
		System.out.println("Total Registros: " + Principal.getTotalRegistros());

	}

	/**
	 * NOS HACE UN PRINCIPAL.DISPOSE() Y HABRE UN NUEVO LOGIN
	 * 
	 * @param controladorPrincipal
	 * @param componentesPrincipal
	 */
	public static void logout(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		((JFrame) componentesPrincipal.get("framePrincipal")).dispose();
		new Login();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner s = (JSpinner) e.getSource();

		if (((JSpinner) componentesPrincipal.get("paginaSpinner")).getValue() == s.getValue()) {
			// TODO HACER QUE NO SE PASE DE LAS PAGINAS MAXIMAS
			if ((int) s.getValue() <= 1) {
				Principal.setPagina(1);
			} else {
				Principal.setPagina((int) s.getValue());
			}
		}
		if (((JSpinner) componentesPrincipal.get("paginadorSpinner")).getValue() == s.getValue()) {
			Principal.setColumnasPagina((int) s.getValue());
		}

		Principal.CrearListarDatos("", this, componentesPrincipal);
	}

}
