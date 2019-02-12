package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import clases.Taller;
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
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.SwingConstants;

public class Principal {

	HashMap<String, Component> componentes = new HashMap<String, Component>();
	public PrincipalController controlador = new PrincipalController(componentes);
	private JFrame frame;
	private static int paginador = 20;
	// private JTextField txtPaginaActual;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
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

	private JScrollPane crearJTable() {

		final JTable table = new JTable(Taller.getTalleres(0, 20), Taller.getTalleresMeta());
		JScrollPane scrollpane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 500));

		return scrollpane;
	}

	private JPanel crearNombreClaseSuperior(ArrayList<String> clases) {
		JPanel panel_clase = new JPanel();

		panel_clase.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JCheckBox chckbxSelecionarTodos = new JCheckBox("Selecionar todos");
		chckbxSelecionarTodos.setHorizontalAlignment(SwingConstants.LEFT);
		panel_clase.add(chckbxSelecionarTodos);

		JLabel lblClase = new JLabel(clases.get(2));
		lblClase.setHorizontalAlignment(SwingConstants.CENTER);
		panel_clase.add(lblClase);

		return panel_clase;
	}

	private JPanel crearBotonesPaginador() {

		JPanel panelPaginatorController = new JPanel();

		JTextField txtPaginadorPaginaActual = new JTextField();
		txtPaginadorPaginaActual.setText("Pagina Actual");
		txtPaginadorPaginaActual.setColumns(10);
		
		txtPaginadorPaginaActual.setEditable(false);

		JButton btnPaginadorSiguente = new JButton(">");
		JButton btnPaginadorUltimo = new JButton(">>");
		JButton btnPaginadorPrimero = new JButton("<<");
		JButton btnPaginadorAnterior = new JButton("<");

		
		//HASMAP DE COMPONENTES DEL PAGINADOR
		componentes.put("btnPaginadorSiguente", btnPaginadorSiguente);
		componentes.put("btnPaginadorUltimo", btnPaginadorUltimo);
		componentes.put("btnPaginadorPrimero",btnPaginadorPrimero );
		componentes.put("btnPaginadorAnterior",btnPaginadorAnterior );
		componentes.put("panelPaginatorController", panelPaginatorController);
		componentes.put("txtPaginadorPaginaActual", txtPaginadorPaginaActual);
		
		panelPaginatorController.add(componentes.get("btnPaginadorPrimero"));
		panelPaginatorController.add(componentes.get("btnPaginadorAnterior"));
		panelPaginatorController.add(componentes.get("txtPaginadorPaginaActual"));
		panelPaginatorController.add(componentes.get("btnPaginadorSiguente"));
		panelPaginatorController.add(componentes.get("btnPaginadorUltimo"));

		btnPaginadorSiguente.addActionListener(controlador);
		btnPaginadorUltimo.addActionListener(controlador);
		btnPaginadorPrimero.addActionListener(controlador);
		btnPaginadorAnterior.addActionListener(controlador);

		return panelPaginatorController;
	}

	private JPanel crearListarDatos(ArrayList<String> data, int paginas) {
		ArrayList<JCheckBox> check = new ArrayList<JCheckBox>();
		ArrayList<JLabel> label = new ArrayList<JLabel>();
		int contadorlabel = 0;
		// Creo panel
		JPanel listado = new JPanel();
		// le asigno un layaout
		listado.setLayout(new GridLayout(paginas, data.size(), 0, 0));

		for (int i = 0; i < paginas; i++) {
			check.add(new JCheckBox(" Seleccionar"));
			listado.add((JCheckBox) check.get(i));

			for (int j = 0; j < data.size() - 1; j++) {
				label.add(new JLabel(data.get(j + 1).toString()));
				listado.add((JLabel) label.get(contadorlabel));
				contadorlabel++;
				// listado.add(new JLabel(data.get(j+1).toString()));

			}

		}
		return listado;
	}

	private JPanel crearListadoValores(ArrayList<String> data) {

		JPanel listadoTop = new JPanel();
		listadoTop.setLayout(new GridLayout(1, data.size(), 0, 0));

		for (int i = 0; i < data.size(); i++) {
			String valor = data.get(i);
			listadoTop.add(new JLabel(valor));
		}

		return listadoTop;
	}

	private JPanel crearAcciones() {

		JPanel panel_acciones = new JPanel();

		panel_acciones.setLayout(new GridLayout(1, 3, 0, 0));

		JButton crearData = new JButton("Crear");
		JButton editarData = new JButton("Editar");
		JButton borrarData = new JButton("Borrar");

		panel_acciones.add(crearData);
		panel_acciones.add(editarData);
		panel_acciones.add(borrarData);

		borrarData.setBackground(new Color(255, 51, 0));
		editarData.setBackground(new Color(255, 255, 51));
		crearData.setBackground(new Color(153, 255, 102));

		borrarData.addActionListener(controlador);
		editarData.addActionListener(controlador);
		crearData.addActionListener(controlador);

		borrarData.setActionCommand("borrar");
		editarData.setActionCommand("editar");
		crearData.setActionCommand("crear");

		// TODO ACTIONLISTENER

		return panel_acciones;
	}

	private JPanel crearFiltros(ArrayList<String> data) {

		JPanel panel_filtros = new JPanel();

		panel_filtros.setLayout(new GridLayout(1, data.size(), 0, 0));

		JButton btnBorrarFiltros = new JButton("Borrar Filtros");
		panel_filtros.add(btnBorrarFiltros);
		for (int i = 0; i < data.size(); i++) {
			panel_filtros.add(new JLabel(data.get(i)));
			if (i == 0) {

			} else {
				panel_filtros.add(new JTextField());
			}

		}

		return panel_filtros;
	}

	private Frame exportador() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		return frame;

	}

	private JMenuBar crearBar() {
		JMenuBar menuBar = new JMenuBar();

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

		JButton citas = new JButton(databaseMeta.get(0));
		JButton clientes = new JButton(databaseMeta.get(1));
		JButton talleres = new JButton(databaseMeta.get(2));
		JButton usuarios = new JButton(databaseMeta.get(3));
		JButton vehiculos = new JButton(databaseMeta.get(4));
		JButton vehiculos_tipos = new JButton(databaseMeta.get(5));

		JSeparator separator_1 = new JSeparator();
		JSeparator separator_2 = new JSeparator();

		JButton importar = new JButton("Importar");
		JButton exportar = new JButton("Exportar");

		JSeparator separator = new JSeparator();

		JButton logout = new JButton("Logout");

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

		logout.addActionListener(controlador);
		importar.addActionListener(controlador);
		exportar.addActionListener(controlador);
		citas.addActionListener(controlador);
		talleres.addActionListener(controlador);
		vehiculos.addActionListener(controlador);
		clientes.addActionListener(controlador);
		usuarios.addActionListener(controlador);
		vehiculos_tipos.addActionListener(controlador);

		logout.setActionCommand("Logout");
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

		ArrayList<String> clases = new ArrayList<>();
		clases.add("Citas");
		clases.add("Talleres");
		clases.add("Vehiculos");
		clases.add("Clientes");
		clases.add("Usuarios");
		clases.add("Tipos de Vehiculos");

		// DEFINIMOS COMPONENTES NECESARIOS
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
		// frame.setMinimumSize(new Dimension(1600,700));

		JPanel panel = new JPanel();
		JPanel acciones = crearAcciones();
		JPanel panel_filtros = crearFiltros(Taller.getTalleresMetaArr());
		// JPanel listadoTop = crearListadoValores(Taller.getTalleresMetaArr());
		JPanel listador = crearListarDatos(Taller.getTalleresMetaArr(), 20);

		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();

		// ESTABLECEMOS LAYAOUT
		panel.setLayout(new BorderLayout(0, 0));
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_3.setLayout(new BorderLayout(0, 0));

		// AÑADIMOS COMPONENTES AL PANEL
		frame.setJMenuBar(crearBar());
		frame.getContentPane().add(panel);

		panel.add(acciones, BorderLayout.NORTH);
		panel.add(panel_1, BorderLayout.CENTER);

		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_1.add(crearNombreClaseSuperior(clases), BorderLayout.NORTH);
		panel_1.add(crearBotonesPaginador(), BorderLayout.SOUTH);

		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_2.add(panel_filtros, BorderLayout.NORTH);

		// panel_3.add(listadoTop, BorderLayout.NORTH);
		panel_3.add(crearJTable(), BorderLayout.CENTER);

		// panel_4.add(CrearJTable(), BorderLayout.CENTER);

	}
}
