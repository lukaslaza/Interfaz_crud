package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import clases.Cita;
import clases.Cliente;
import clases.Taller;
import clases.Usuario;
import clases.Vehiculo;
import clases.Vehiculo_Tipo;
import database.funciones;
import funciones.funcionesError;
import interfaz.Creador;
import interfaz.Editor;
import interfaz.Exportador;
import interfaz.GeneradorDom;
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

	public static void borrarDatos(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		for (int i = 0; i < Principal.getColumnasPagina(); i++) {
			if (((JTable) componentesPrincipal.get("table")).getModel()
					.getValueAt(i, funciones.getMetadatosTablaArray().size() - 1).equals(true)) {
				switch (Principal.getClaseActual().toLowerCase()) {
				case "cita":
					Cita cita = new Cita();
					cita.setFecha((Date) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					cita.setHora((Time) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					cita.setKm((int) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 2));
					cita.setId_vehiculo(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 3));
					cita.setId_taller(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 4));
					cita.borrarse();

					break;
				case "taller":
					Taller taller = new Taller();
					taller.setNombre((String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					taller.setDireccion(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					taller.setTelefono(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 2));
					taller.setLatitud((float) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 3));
					taller.setLongitud(
							(float) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 4));
					taller.borrarse();
					break;
				case "vehiculo":
					Vehiculo vehiculo = new Vehiculo();

					vehiculo.setMatricula(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					vehiculo.setMarca(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					vehiculo.setModelo(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 2));
					vehiculo.setAnno((int) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 3));
					vehiculo.setColor(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 4));
					vehiculo.setId_cliente(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 5));
					vehiculo.setId_vehiculo_tipo(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 6));
					vehiculo.borrarse();
					break;
				case "vehiculo_tipo":
					Vehiculo_Tipo vehiculo_Tipo = new Vehiculo_Tipo();

					vehiculo_Tipo
							.setId((String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));

					vehiculo_Tipo.setDescripcion(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					vehiculo_Tipo.borrarse();

					break;
				case "cliente":
					Cliente cliente = new Cliente();

					cliente.setDni((String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					cliente.setNombre(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					cliente.setApellidos(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 2));
					cliente.setTelefono(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 3));
					cliente.setDireccion(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 4));
					cliente.setId_usuario(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 5));
					cliente.borrarse();
					break;
				case "usuario":
					Usuario usuario = new Usuario();

					usuario.setUsuario(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					usuario.setClave((String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));

					usuario.borrarse();
					break;

				default:
					System.out.println("Tienes que actualizar el metodo de borrarDatos()");
					break;
				}
			}
		}

	}

	public static void EditarDatos(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		for (int i = 0; i < Principal.getColumnasPagina(); i++) {
			if (((JTable) componentesPrincipal.get("table")).getModel()
					.getValueAt(i, funciones.getMetadatosTablaArray().size() - 1).equals(true)) {
				switch (Principal.getClaseActual().toLowerCase()) {
				case "cita":
					Cita cita = new Cita();
					cita.setFecha((Date) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					cita.setHora((Time) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					cita.setKm((int) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 2));
					cita.setId_vehiculo(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 3));
					cita.setId_taller(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 4));
					new Editor(cita);

					break;
				case "taller":
					Taller taller = new Taller();
					taller.setNombre((String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					taller.setDireccion(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					taller.setTelefono(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 2));
					taller.setLatitud((float) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 3));
					taller.setLongitud(
							(float) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 4));
					new Editor(taller);
					break;
				case "vehiculo":
					Vehiculo vehiculo = new Vehiculo();

					vehiculo.setMatricula(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					vehiculo.setMarca(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					vehiculo.setModelo(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 2));
					vehiculo.setAnno((int) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 3));
					vehiculo.setColor(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 4));
					vehiculo.setId_cliente(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 5));
					vehiculo.setId_vehiculo_tipo(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 6));
					new Editor(vehiculo);
					break;
				case "vehiculo_tipo":
					Vehiculo_Tipo vehiculo_Tipo = new Vehiculo_Tipo();

					vehiculo_Tipo
							.setId((String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));

					vehiculo_Tipo.setDescripcion(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					new Editor(vehiculo_Tipo);

					break;
				case "cliente":
					Cliente cliente = new Cliente();

					cliente.setDni((String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					cliente.setNombre(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));
					cliente.setApellidos(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 2));
					cliente.setTelefono(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 3));
					cliente.setDireccion(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 4));
					cliente.setId_usuario(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 5));
					new Editor(cliente);
					break;
				case "usuario":
					Usuario usuario = new Usuario();

					usuario.setUsuario(
							(String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 0));
					usuario.setClave((String) ((JTable) componentesPrincipal.get("table")).getModel().getValueAt(i, 1));

					new Editor(usuario);
					break;

				default:
					System.out.println("Tienes que actualizar el metodo de editarDatos()");
					break;
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String accion = arg0.getActionCommand();

		if (accion.equalsIgnoreCase("crear")) {
			new Creador();
			database.funciones.leerFiltros(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("Borrar")) {
			borrarDatos(this, componentesPrincipal);
			database.funciones.leerFiltros(this, componentesPrincipal);
		}
		if (accion.equalsIgnoreCase("editar")) {
			EditarDatos(this, componentesPrincipal);
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
			GeneradorDom generadorDom;
			try {
				generadorDom = new GeneradorDom();

				generadorDom.generarDocument(this, componentesPrincipal);

				generadorDom.generarXml();

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Exportador.getInstance();
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

		// Paginador
		if (accion.equalsIgnoreCase("paginaSiguente")) {
			if (!(Principal.getPagina() > Principal.getTotalRegistros() / Principal.getColumnasPagina())) {
				Principal.setPagina(Principal.getPagina() + 1);
				database.funciones.leerFiltros(this, componentesPrincipal);
			}
		}
		if (accion.equalsIgnoreCase("ultimaPagina")) {
			funciones.getColumnMax("");
			Principal.setPagina(
					(int) Math.ceil(((float) Principal.getTotalRegistros()) / ((float) Principal.getColumnasPagina())));
			database.funciones.leerFiltros(this, componentesPrincipal);

		}
		if (accion.equalsIgnoreCase("primeraPagina")) {
			Principal.setPagina(1);
			database.funciones.leerFiltros(this, componentesPrincipal);
		}

		if (accion.equalsIgnoreCase("paginaAnterior")) {
			if (!(Principal.getPagina() == 1)) {
				Principal.setPagina(Principal.getPagina() - 1);
				database.funciones.leerFiltros(this, componentesPrincipal);
			}

		}

		((JSpinner) componentesPrincipal.get("paginaSpinner")).setValue(Principal.getPagina());
		System.out.println("Pagina Actual:" + Principal.getPagina());
		System.out.println("Total Registros por pagina: " + Principal.getTotalRegistros());

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

		database.funciones.leerFiltros(this, componentesPrincipal);
	}

}
