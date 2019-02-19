package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import clases.Cita;
import clases.Cliente;
import clases.Taller;
import clases.Usuario;
import clases.dbConexion;
import controller.PrincipalController;
import database.funciones;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.JList;
import javax.print.attribute.standard.Sides;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Window;

import javax.swing.SwingConstants;
//TODO PONER TAMA�O MINIMO A LA VENTANA

public class Principal {
	// VARIABLES DE LA CLASE
	private static String claseActual = "taller";
	private static int pagina = 1;
	private static int columnasPagina = 30;
	private static int totalRegistros = 1000;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					// window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * LLAMA A UN METODO UNIVERSAL QUE CREA LOS FILTROS EN BASE A LA CLASE ACTUAL
	 * LOS METE EN UN PANEL Y ESTE SE LE ASGINA AL PANEL TOP.SOUTH
	 * 
	 * @param controladorPrincipal
	 * @param componentesPrincipal
	 */
	public static void CrearFiltros(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		JPanel panelFiltros = funciones.crearFiltros(controladorPrincipal, componentesPrincipal);
		componentesPrincipal.put("panelFiltros", panelFiltros);

		componentesPrincipal.get("panelFiltros").revalidate();
		componentesPrincipal.get("panelFiltros").repaint();

		((JPanel) componentesPrincipal.get("top")).add(panelFiltros, BorderLayout.SOUTH);
		((JPanel) componentesPrincipal.get("top")).revalidate();
		((JPanel) componentesPrincipal.get("top")).repaint();

	}

	/**
	 * CREA UN JTABLE QUE HEREDA DE UN ABSTRACTMODELTABLE PARA CREARLO Y LO INSERTA EN UN JSCROLLPANE
	 * @param sentencia SI ESTA VACIA CONSIGUE LOS DATOS AUTOMATICAMENTE, SI NO USA LA SENTENCIA PARA CONSEGUIR LOS DATOS
	 * @param controladorPrincipal
	 * @param componentesPrincipal
	 */
	public static void CrearListarDatos(String sentencia, PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		TablaListar tabla = null;
		JTable table = new JTable(new TablaListar());
		if (sentencia.equalsIgnoreCase(" ") || sentencia.isEmpty() || sentencia == null) {
			tabla = new TablaListar();
		} else {
			tabla = new TablaListar(funciones.getDatos(sentencia), funciones.getMetadatosTabla());
		}
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		// CREO QUE ES INNECESARIA
		table.setModel(tabla);

		componentesPrincipal.put("table", table);

		table.getModel().addTableModelListener(controladorPrincipal);

		((JScrollPane) componentesPrincipal.get("datos")).setViewportView(table);
		((JScrollPane) componentesPrincipal.get("datos")).revalidate();
		((JScrollPane) componentesPrincipal.get("datos")).repaint();

	}

	/**
	 * CREA UN PANEL QUE CONTIENE UN FLOWLAYOUT.LEFT JLABEL Y UN SPINNER CREA UN
	 * PANEL QUE CONTIENE LOS BOTONES DEL PAGINADOR Y EL SPINNER
	 * 
	 * @param controladorPrincipal
	 * @param componentesPrincipal
	 */
	private static void CrearPaginador(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {

		// LADO IZQUIERDO
		JPanel panelPaginadorWest = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel paginas = new JLabel();
		paginas.setHorizontalAlignment(SwingConstants.LEFT);
		paginas.setText("Registros:");

		SpinnerModel paginadorValoresSpinner = new SpinnerNumberModel(getColumnasPagina(), 30, 3500, 5);

		JSpinner paginadorSpinner = new JSpinner(paginadorValoresSpinner);
		paginadorSpinner.setBounds(100, 100, 50, 30);

		componentesPrincipal.put("paginadorSpinner", paginadorSpinner);

		paginadorSpinner.addChangeListener(controladorPrincipal);

		panelPaginadorWest.add(paginas);
		panelPaginadorWest.add(paginadorSpinner);

		((JPanel) componentesPrincipal.get("paginador")).add(panelPaginadorWest);

		// LADO CENTRAL
		JPanel panelPaginadorCenter = new JPanel();
		SpinnerModel paginaValoresSpinner = new SpinnerNumberModel(getPagina(), 1, 300, 1);

		JSpinner paginaSpinner = new JSpinner(paginaValoresSpinner);
		paginaSpinner.setBounds(100, 100, 50, 30);

		JButton btnPaginadorPrimero = new JButton("<<");
		JButton btnPaginadorAnterior = new JButton("<");

		JButton btnPaginadorSiguente = new JButton(">");
		JButton btnPaginadorUltimo = new JButton(">>");

		// HASMAP DE componentesPrincipal DEL PAGINADOR
		componentesPrincipal.put("btnPaginadorSiguente", btnPaginadorSiguente);
		componentesPrincipal.put("btnPaginadorUltimo", btnPaginadorUltimo);
		componentesPrincipal.put("btnPaginadorPrimero", btnPaginadorPrimero);
		componentesPrincipal.put("btnPaginadorAnterior", btnPaginadorAnterior);
		componentesPrincipal.put("panelPaginadorCenter", panelPaginadorCenter);
		componentesPrincipal.put("paginaSpinner", paginaSpinner);

		// panelPaginador.add(paginadorSpinner);
		panelPaginadorCenter.add(btnPaginadorPrimero);
		panelPaginadorCenter.add(btnPaginadorAnterior);
		panelPaginadorCenter.add(paginaSpinner);
		panelPaginadorCenter.add(btnPaginadorSiguente);
		panelPaginadorCenter.add(btnPaginadorUltimo);

		btnPaginadorSiguente.setActionCommand("paginaSiguente");
		btnPaginadorUltimo.setActionCommand("ultimaPagina");
		btnPaginadorPrimero.setActionCommand("primeraPagina");
		btnPaginadorAnterior.setActionCommand("paginaAnterior");

		btnPaginadorSiguente.addActionListener(controladorPrincipal);
		btnPaginadorUltimo.addActionListener(controladorPrincipal);
		btnPaginadorPrimero.addActionListener(controladorPrincipal);
		btnPaginadorAnterior.addActionListener(controladorPrincipal);
		paginaSpinner.addChangeListener(controladorPrincipal);

		((JPanel) componentesPrincipal.get("paginador")).add(panelPaginadorCenter);

	}

	/**
	 * CREA EL PANEL CON LOS BOTONES SUPERIORES DE CREAR, EDITAR Y BORRAR; SE LOS
	 * ASIGNA AL PANEL TOP.NORTH EL PANEL TIENE UN GRIDLAYOUT --> 1 Fila, 3
	 * Columnas.
	 * 
	 * @param controladorPrincipal
	 * @param componentesPrincipal
	 */
	private static void crearAcciones(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {

		JPanel panel_acciones = new JPanel();
		panel_acciones.setLayout(new GridLayout(1, 3, 0, 0));

		JButton accionesCrearDato = new JButton("Crear");
		JButton accionesEditarDato = new JButton("Editar");
		JButton accionesBorrarDato = new JButton("Borrar");

		panel_acciones.add(accionesCrearDato);
		panel_acciones.add(accionesEditarDato);
		panel_acciones.add(accionesBorrarDato);

		accionesBorrarDato.setBackground(new Color(255, 50, 0));
		accionesEditarDato.setBackground(new Color(255, 255, 50));
		accionesCrearDato.setBackground(new Color(150, 255, 100));

		componentesPrincipal.put("accionesCrearDato", accionesCrearDato);
		componentesPrincipal.put("accionesEditarDato", accionesEditarDato);
		componentesPrincipal.put("accionesBorrarDato", accionesBorrarDato);

		accionesBorrarDato.setActionCommand("borrar");
		accionesEditarDato.setActionCommand("editar");
		accionesCrearDato.setActionCommand("crear");

		accionesBorrarDato.addActionListener(controladorPrincipal);
		accionesEditarDato.addActionListener(controladorPrincipal);
		accionesCrearDato.addActionListener(controladorPrincipal);

		componentesPrincipal.put("panel_acciones", panel_acciones);

		((JPanel) componentesPrincipal.get("top")).add(panel_acciones, BorderLayout.NORTH);
	}

	/**
	 * CREA EL LA BARRA CON LOS BOTONES Y SUS ACTION LISTENER CORRESPONDIENTES Y SE
	 * LO ASIGNA AL FRAME
	 * 
	 * @param controladorPrincipal
	 * @param componentesPrincipal
	 */
	private static void crearBar(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {

		JMenuBar menuBar = new JMenuBar();

		// Consigue los nombres de las tablas de la base de datos
		Connection conn = dbConexion.getConnection();
		ArrayList<String> databaseMeta = new ArrayList();
		DatabaseMetaData md;
		try {
			md = conn.getMetaData();

			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				databaseMeta.add(rs.getString(3).toUpperCase());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// A�adimos el texto conseguido anteriormente a los botones
		JButton citas = new JButton(databaseMeta.get(0));
		JButton clientes = new JButton(databaseMeta.get(1));
		JButton talleres = new JButton(databaseMeta.get(2));
		JButton usuarios = new JButton(databaseMeta.get(3));
		JButton vehiculos = new JButton(databaseMeta.get(4));
		JButton vehiculos_tipos = new JButton(databaseMeta.get(5).replace("_", " "));

		JSeparator separator_1 = new JSeparator();
		JSeparator separator_2 = new JSeparator();

		JButton importar = new JButton("Importar".toUpperCase());
		JButton exportar = new JButton("Exportar".toUpperCase());

		JSeparator separator = new JSeparator();

		JButton logout = new JButton("Logout".toUpperCase());

		// A�adir Botones
		menuBar.add(citas);
		menuBar.add(talleres);
		menuBar.add(vehiculos);
		menuBar.add(clientes);
		menuBar.add(usuarios);
		menuBar.add(vehiculos_tipos);

		menuBar.add(separator_1);
		menuBar.add(separator_2);

		menuBar.add(importar);

		menuBar.add(exportar);

		menuBar.add(separator);

		menuBar.add(logout);

		logout.addActionListener(controladorPrincipal);
		importar.addActionListener(controladorPrincipal);
		exportar.addActionListener(controladorPrincipal);
		citas.addActionListener(controladorPrincipal);
		talleres.addActionListener(controladorPrincipal);
		vehiculos.addActionListener(controladorPrincipal);
		clientes.addActionListener(controladorPrincipal);
		usuarios.addActionListener(controladorPrincipal);
		vehiculos_tipos.addActionListener(controladorPrincipal);

		logout.setActionCommand("logout");
		importar.setActionCommand("importar");
		exportar.setActionCommand("exportar");
		citas.setActionCommand("citas");
		talleres.setActionCommand("talleres");
		vehiculos.setActionCommand("vehiculos");
		clientes.setActionCommand("clientes");
		usuarios.setActionCommand("usuarios");
		vehiculos_tipos.setActionCommand("vehiculos_tipos");

		((JFrame) componentesPrincipal.get("framePrincipal")).setJMenuBar(menuBar);

	}

	/**
	 * CREA LA VENTANA Y LAS VISTAS DE ESTA
	 */
	private void initialize() {

		// COMPONENTES
		Frame framePrincipal = new JFrame();
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout(0, 0));
		JPanel topFiltros = new JPanel();
		JScrollPane datos = new JScrollPane();
		JPanel paginador = new JPanel();
		paginador.setLayout(new GridLayout(0, 3, 0, 0));

		framePrincipal.setBounds(100, 100, 1000, 700);
		((JFrame) framePrincipal).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		((JFrame) framePrincipal).getContentPane().setLayout(new BorderLayout(0, 0));
		framePrincipal.setVisible(true);

		// INSERTAR DATOS AL HASHMAP
		HashMap<String, Component> componentesPrincipal = new HashMap<String, Component>();

		componentesPrincipal.put("framePrincipal", framePrincipal);
		componentesPrincipal.put("top", top);
		componentesPrincipal.put("topFiltros", topFiltros);
		componentesPrincipal.put("datos", datos);
		componentesPrincipal.put("paginador", paginador);

		// CONTROLADOR
		PrincipalController controladorPrincipal = new PrincipalController(componentesPrincipal);

		// ASIGANCION DE VALORES A LOS COMPONENTES
		crearAcciones(controladorPrincipal, componentesPrincipal);
		crearBar(controladorPrincipal, componentesPrincipal);
		CrearFiltros(controladorPrincipal, componentesPrincipal);
		CrearListarDatos("",controladorPrincipal, componentesPrincipal);
		CrearFiltros(controladorPrincipal, componentesPrincipal);
		CrearPaginador(controladorPrincipal, componentesPrincipal);

		((JFrame) framePrincipal).getContentPane().add(top, BorderLayout.NORTH);
		((JFrame) framePrincipal).getContentPane().add(datos, BorderLayout.CENTER);
		((JFrame) framePrincipal).getContentPane().add(paginador, BorderLayout.SOUTH);
	}

	public static int getPagina() {
		return pagina;
	}

	public static void setPagina(int pagina) {
		if (pagina <= 1) {
			Principal.pagina = 1;
		} else {
			Principal.pagina = pagina;
		}
	}

	public static int getColumnasPagina() {
		return columnasPagina;
	}

	public static void setColumnasPagina(int columnasPagina) {

		if (columnasPagina <= 30) {
			Principal.columnasPagina = 30;
		} else {
			Principal.columnasPagina = columnasPagina;
		}
	}

	public static String getClaseActual() {
		return claseActual;
	}

	public static void setClaseActual(String claseActual) {
		Principal.claseActual = claseActual.toLowerCase();
	}

	public static int getTotalRegistros() {
		return totalRegistros;
	}

	public static void setTotalRegistros(int totalRegistros) {
		Principal.totalRegistros = totalRegistros;
	}
}
