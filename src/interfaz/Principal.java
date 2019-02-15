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
//TODO PONER TAMAÑO MINIMO A LA VENTANA

public class Principal {

	private JFrame frame;

	private static String claseActual = "vehiculo";
	private static int pagina = 0;
	private static int columnasPagina = 20;
	private static int totalRegistros = 1000;
	// private JTextField txtPaginaActual;

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

	public static void listarDatos(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {
		System.out.println(getClaseActual());
		
			TablaListar tablaTaller = Principal.crearJTable(database.funciones.getDatos(pagina, columnasPagina),
					database.funciones.getMetadatosTabla());
			((JPanel) componentesPrincipal.get("panel_listar")).add(tablaTaller, BorderLayout.CENTER);

	}

	/**
	 * Crea la tabla con nuestra clase TablaListar
	 * 
	 * @param data        datos a mostrar
	 * @param columnNames cabeceras de las columnas
	 * @return la tabla para poder utilizarla
	 */
	public static TablaListar crearJTable(Object[][] data, String[] columnNames) {
		TablaListar table = new TablaListar(data, columnNames);
		return table;
	}

	/**
	 * Crea el Jlable y spinner lateral para poder seleccionar la cantidad de
	 * registros por pagina
	 * 
	 * @return
	 */
	private JPanel crearBotonesPaginadorWest(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {

		JPanel panelPaginadorWest = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel paginas = new JLabel();
		paginas.setHorizontalAlignment(SwingConstants.LEFT);
		paginas.setText("Registros:");

		SpinnerModel paginadorValoresSpinner = new SpinnerNumberModel(35, 35, 3500, 5);

		JSpinner paginadorSpinner = new JSpinner(paginadorValoresSpinner);
		paginadorSpinner.setBounds(100, 100, 50, 30);

		componentesPrincipal.put("paginadorSpinner", paginadorSpinner);
		paginadorSpinner.addChangeListener(controladorPrincipal);
		panelPaginadorWest.add(paginas);
		panelPaginadorWest.add(paginadorSpinner);

		return panelPaginadorWest;
	}

	private JPanel crearBotonesPaginadorCentro(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {

		JPanel panelPaginador = new JPanel();
		SpinnerModel paginadorValoresSpinner = new SpinnerNumberModel(35, 35, 3500, 5);
		SpinnerModel paginaValoresSpinner = new SpinnerNumberModel(1, 1, 300, 1);

		JSpinner paginadorSpinner = new JSpinner(paginadorValoresSpinner);
		paginadorSpinner.setBounds(100, 100, 50, 30);

		JSpinner paginaSpinner = new JSpinner(paginaValoresSpinner);
		paginaSpinner.setBounds(100, 100, 50, 30);

		JButton btnPaginadorSiguente = new JButton(">");
		JButton btnPaginadorUltimo = new JButton(">>");
		JButton btnPaginadorPrimero = new JButton("<<");
		JButton btnPaginadorAnterior = new JButton("<");

		// HASMAP DE componentesPrincipal DEL PAGINADOR
		componentesPrincipal.put("btnPaginadorSiguente", btnPaginadorSiguente);
		componentesPrincipal.put("btnPaginadorUltimo", btnPaginadorUltimo);
		componentesPrincipal.put("btnPaginadorPrimero", btnPaginadorPrimero);
		componentesPrincipal.put("btnPaginadorAnterior", btnPaginadorAnterior);
		componentesPrincipal.put("panelPaginador", panelPaginador);
		componentesPrincipal.put("paginaSpinner", paginaSpinner);
		componentesPrincipal.put("paginadorSpinner", paginadorSpinner);

		// panelPaginador.add(paginadorSpinner);
		panelPaginador.add(btnPaginadorPrimero);
		panelPaginador.add(btnPaginadorAnterior);
		panelPaginador.add(paginaSpinner);
		panelPaginador.add(btnPaginadorSiguente);
		panelPaginador.add(btnPaginadorUltimo);

		btnPaginadorSiguente.addActionListener(controladorPrincipal);
		btnPaginadorUltimo.addActionListener(controladorPrincipal);
		btnPaginadorPrimero.addActionListener(controladorPrincipal);
		btnPaginadorAnterior.addActionListener(controladorPrincipal);

		return panelPaginador;
	}

	public static void logout(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {

	}

	/**
	 * FINALIZADO NO TOCAR
	 * 
	 * Crea los 3 botones de las acciones de crear, borrar y editar
	 * 
	 * @return
	 */
	private JPanel crearAcciones(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) {

		JPanel panel_acciones = new JPanel();

		panel_acciones.setLayout(new GridLayout(1, 3, 0, 0));

		JButton accionesCrearDato = new JButton("Crear");
		JButton accionesEditarDato = new JButton("Editar");
		JButton accionesBorrarDato = new JButton("Borrar");

		componentesPrincipal.put("accionesCrearDato", accionesCrearDato);
		componentesPrincipal.put("accionesEditarDato", accionesEditarDato);
		componentesPrincipal.put("accionesBorrarDato", accionesBorrarDato);

		panel_acciones.add(componentesPrincipal.get("accionesCrearDato"));
		panel_acciones.add(componentesPrincipal.get("accionesEditarDato"));
		panel_acciones.add(componentesPrincipal.get("accionesBorrarDato"));

		accionesBorrarDato.setBackground(new Color(255, 51, 0));
		accionesEditarDato.setBackground(new Color(255, 255, 51));
		accionesCrearDato.setBackground(new Color(153, 255, 102));

		accionesBorrarDato.addActionListener(controladorPrincipal);
		accionesEditarDato.addActionListener(controladorPrincipal);
		accionesCrearDato.addActionListener(controladorPrincipal);

		accionesBorrarDato.setActionCommand("borrar");
		accionesEditarDato.setActionCommand("editar");
		accionesCrearDato.setActionCommand("crear");

		// TODO ACTIONLISTENER

		return panel_acciones;
	}

	

	

	private Frame exportador() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		return frame;

	}

	// TODO HACER QUE CUANDO PULSE LOGOUT SE CIERRE LA VENTANA Y SE CREE UN NUEVO
	// LOGIN
	private JMenuBar crearBar(PrincipalController controladorPrincipal,
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

//Añadimos el texto conseguido anteriormente a los botones
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

		// Añadir Botones
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

		// logout.addActionListener(controladorPrincipal);
		importar.addActionListener(controladorPrincipal);
		exportar.addActionListener(controladorPrincipal);
		citas.addActionListener(controladorPrincipal);
		talleres.addActionListener(controladorPrincipal);
		vehiculos.addActionListener(controladorPrincipal);
		clientes.addActionListener(controladorPrincipal);
		usuarios.addActionListener(controladorPrincipal);
		vehiculos_tipos.addActionListener(controladorPrincipal);

		// logout.setActionCommand("Logout");
		importar.setActionCommand("importar");
		exportar.setActionCommand("exportar");
		citas.setActionCommand("citas");
		talleres.setActionCommand("talleres");
		vehiculos.setActionCommand("vehiculos");
		clientes.setActionCommand("clientes");
		usuarios.setActionCommand("usuarios");
		vehiculos_tipos.setActionCommand("vehiculos_tipos");

		return menuBar;

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		HashMap<String, Component> componentesPrincipal = new HashMap<String, Component>();

		// DEFINIMOS componentesPrincipal NECESARIOS
		Frame framePrincipal = new JFrame();
		framePrincipal.setBounds(100, 100, 1000, 700);
		((JFrame) framePrincipal).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		((JFrame) framePrincipal).getContentPane().setLayout(new BorderLayout(0, 0));
		framePrincipal.setVisible(true);

		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();

		JPanel panel_listar = new JPanel();
		JPanel panelPaginador = new JPanel();
		JPanel acciones = new JPanel();
		JPanel panel_filtros = new JPanel();

		componentesPrincipal.put("framePrincipal", framePrincipal);
		componentesPrincipal.put("panel_listar", panel_listar);

		// controladorPrincipal DE LA CLASE
		PrincipalController controladorPrincipal = new PrincipalController(componentesPrincipal);

		acciones = crearAcciones(controladorPrincipal, componentesPrincipal);
		panel_filtros =database.funciones.crearFiltros(controladorPrincipal, componentesPrincipal);

		// ESTABLECEMOS LAYAOUT
		panel.setLayout(new BorderLayout(0, 0));
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_listar.setLayout(new BorderLayout(0, 0));

		// AÑADIMOS componentesPrincipal AL PANEL
		((JFrame) framePrincipal).setJMenuBar(crearBar(controladorPrincipal, componentesPrincipal));
		((JFrame) framePrincipal).getContentPane().add(panel);

		panel.add(acciones, BorderLayout.NORTH);
		panel.add(panel_1, BorderLayout.CENTER);

		panel_1.add(panel_2, BorderLayout.CENTER);
		// panel_1.add(crearNombreClaseSuperior(clases), BorderLayout.NORTH);

		panel_1.add(panelPaginador, BorderLayout.SOUTH);

		panelPaginador.setLayout(new GridLayout(0, 3, 0, 0));
		panelPaginador.add(crearBotonesPaginadorWest(controladorPrincipal, componentesPrincipal));

		JPanel panelVacioPaginador = new JPanel();
		panelPaginador.add(crearBotonesPaginadorCentro(controladorPrincipal, componentesPrincipal));
		panelPaginador.add(panelVacioPaginador);

		panel_2.add(panel_listar, BorderLayout.CENTER);
		panel_2.add(panel_filtros, BorderLayout.NORTH);

		// panel_listar.add(listadoTop, BorderLayout.NORTH);
		// panel_listar.add(Principal.crearJTable(Taller.getTalleres(pagina,
		// columnasPagina),
		// Taller.getTalleresMeta()), BorderLayout.CENTER);
		// panel_listar.add(Principal.crearJTable(Cita.getCitas(),
		// Usuario.getUsuarioColumnNames()), BorderLayout.CENTER);

		// panel_4.add(CrearJTable(), BorderLayout.CENTER);

		listarDatos(controladorPrincipal, componentesPrincipal);
	}

	public static int getPagina() {
		return pagina;
	}

	public static void setPagina(int pagina) {
		Principal.pagina = pagina;
	}

	public static int getColumnasPagina() {
		return columnasPagina;
	}

	public static void setColumnasPagina(int columnasPagina) {
		Principal.columnasPagina = columnasPagina;
	}

	public static String getClaseActual() {
		return claseActual;
	}

	public static void setClaseActual(String claseActual) {
		Principal.claseActual = claseActual;
	}

	public static int getTotalRegistros() {
		return totalRegistros;
	}

	public static void setTotalRegistros(int totalRegistros) {
		Principal.totalRegistros = totalRegistros;
	}
}
