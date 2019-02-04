package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.PrincipalController;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTree;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.SwingConstants;

public class Principal {

	public PrincipalController controlador = new PrincipalController();
	private JFrame frame;
	// private JTextField txtPaginaActual;

	/**
	 * Launch the application.
	 */
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

		JPanel paginatorController = new JPanel();

		JTextField txtPaginaActual = new JTextField();
		// TODO debe contener un int con la pagina actual, que se sacaria diviendo las
		// flas del array por el numero de paginador escocido
		txtPaginaActual.setText("Pagina Actual");
		txtPaginaActual.setColumns(10);

		JButton btnsiguente = new JButton(">");
		JButton btnultimo = new JButton(">>");
		JButton btnprimero = new JButton("<<");
		JButton btnanterior = new JButton("<");

		paginatorController.add(btnprimero);
		paginatorController.add(btnanterior);
		paginatorController.add(txtPaginaActual);
		paginatorController.add(btnsiguente);
		paginatorController.add(btnultimo);

		return paginatorController;
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

	private JMenuBar crearBar(ArrayList<String> clases, ArrayList<String> datos_ex) {
		JMenuBar menuBar = new JMenuBar();

		JButton citas = new JButton(clases.get(0));
		menuBar.add(citas);

		JButton talleres = new JButton(clases.get(1));
		menuBar.add(talleres);

		JButton vehiculos = new JButton(clases.get(2));
		menuBar.add(vehiculos);

		JButton clientes = new JButton(clases.get(3));
		menuBar.add(clientes);

		JButton usuarios = new JButton(clases.get(4));
		menuBar.add(usuarios);

		JButton vehiculos_tipos = new JButton(clases.get(5));
		menuBar.add(vehiculos_tipos);

		JSeparator separator_1 = new JSeparator();
		menuBar.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		menuBar.add(separator_2);

		// TODO contendra los metodos para exportar y importar datos a la base de datos
		JButton importar = new JButton("Importar");

		menuBar.add(importar);

		JButton exportar = new JButton("Exportar");
		menuBar.add(exportar);

		JSeparator separator = new JSeparator();
		menuBar.add(separator);

		JButton logout = new JButton("Logout");
		menuBar.add(logout);
		logout.addActionListener(controlador);
		logout.setActionCommand("Logout");

		importar.addActionListener(controlador);
		importar.setActionCommand("importar");

		exportar.addActionListener(controlador);
		exportar.setActionCommand("exportar");
		
	
		return menuBar;

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		ArrayList<String> nombre = new ArrayList<>();
		nombre.add("  ");
		nombre.add("id");
		nombre.add("nombre");
		nombre.add("apellidos");
		nombre.add("numero");
		nombre.add("calle");
		nombre.add("nacionalidad");
		nombre.add("fecha_nacimiento");

		ArrayList<String> clases = new ArrayList<>();
		clases.add("Citas");
		clases.add("Talleres");
		clases.add("Vehiculos");
		clases.add("Clientes");
		clases.add("Usuarios");
		clases.add("Tipos de Vehiculos");

		ArrayList<String> datos = new ArrayList<>();
		datos.add("Importar");
		datos.add("Exportar");

		// DEFINIMOS COMPONENTES NECESARIOS
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);
		// frame.setMinimumSize(new Dimension(1600,700));

		JPanel panel = new JPanel();
		JPanel acciones = crearAcciones();
		JPanel panel_filtros = crearFiltros(nombre);
		JPanel listadoTop = crearListadoValores(nombre);
		JPanel listador = crearListarDatos(nombre, 20);

		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();

		// ESTABLECEMOS LAYAOUT
		panel.setLayout(new BorderLayout(0, 0));
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_3.setLayout(new BorderLayout(0, 0));

		// AÑADIMOS COMPONENTES AL PANEL
		frame.setJMenuBar(crearBar(clases, datos));
		frame.getContentPane().add(panel);

		panel.add(acciones, BorderLayout.NORTH);
		panel.add(panel_1, BorderLayout.CENTER);

		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_1.add(crearNombreClaseSuperior(clases), BorderLayout.NORTH);
		panel_1.add(crearBotonesPaginador(), BorderLayout.SOUTH);

		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_2.add(panel_filtros, BorderLayout.NORTH);

		panel_3.add(listadoTop, BorderLayout.NORTH);
		panel_3.add(listador, BorderLayout.CENTER);

	}
}
