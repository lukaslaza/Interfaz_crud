package interfaz;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import controller.EditorController;
import controller.EditorController;
import controller.PrincipalController;
import database.funciones;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.time.format.DateTimeFormatterBuilder;

import javax.swing.SwingConstants;

import clases.Cita;
import clases.Cliente;
import clases.Taller;
import clases.Usuario;
import clases.Vehiculo;
import clases.Vehiculo_Tipo;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

public class Editor {
	private static Editor instancia = null;

	public static Editor getInstance(Object clase) {
		if (instancia == null) {
			instancia = new Editor(clase);
		}
		return instancia;
	}

	

	/**
	 * Create the application.
	 */
	public Editor(Object clase) {
		initialize(clase);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object clase) {

		HashMap<String, Component> componentesEditor = new HashMap<String, Component>();

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 240, 401);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		componentesEditor.put("frame", frame);

		JPanel titulo = new JPanel();
		frame.getContentPane().add(titulo, BorderLayout.NORTH);

		JLabel lblClase = new JLabel(Principal.getClaseActual().toUpperCase());
		lblClase.setFont(new Font("Verdana", Font.BOLD, 25));
		lblClase.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.add(lblClase);

		JPanel botones = new JPanel();
		frame.getContentPane().add(botones, BorderLayout.SOUTH);

		JButton btnEditorInsertar = new JButton("Modificar");
		JButton btnEditorCancelar = new JButton("Cancelar");

		btnEditorInsertar.setActionCommand("InsertarEditor");
		btnEditorCancelar.setActionCommand("CancelarEditor");

		componentesEditor.put("btnEditorCancelar", btnEditorCancelar);
		componentesEditor.put("btnEditorInsertar", btnEditorInsertar);
		componentesEditor.put("frame", frame);

		botones.add(btnEditorInsertar);
		botones.add(btnEditorCancelar);

		JPanel formulario = new JPanel();
		frame.getContentPane().add(formulario, BorderLayout.CENTER);

		formulario.setLayout(new MigLayout("", "[89px][89px]", "[87px][87px]"));

		for (int i = 0; i < funciones.getMetadatosTablaArray().size() - 1; i++) {
			componentesEditor.put("lblEditor" + funciones.getMetadatosTablaArray().get(i),
					new JLabel(funciones.getMetadatosTablaArray().get(i) + " -->"));
			componentesEditor.put("txtEditor" + funciones.getMetadatosTablaArray().get(i), new JFormattedTextField());

			formulario.add(componentesEditor.get("lblEditor" + funciones.getMetadatosTablaArray().get(i)),
					"cell 0 " + i + ",growx,aligny center");
			formulario.add(componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(i)),
					"cell 1 " + i + ",growx,aligny center");
		}

		if (clase instanceof Taller) {
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(0)))
					.setText("" + ((Taller) clase).getNombre());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(1)))
					.setText("" + ((Taller) clase).getDireccion());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(2)))
					.setText("" + ((Taller) clase).getTelefono());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(3)))
					.setText("" + ((Taller) clase).getLatitud());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(4)))
					.setText("" + ((Taller) clase).getLongitud());

		}
		if (clase instanceof Cita) {
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(0)))
					.setText("" + ((Cita) clase).getFecha());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(1)))
					.setText("" + ((Cita) clase).getHora());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(2)))
					.setText("" + ((Cita) clase).getKm());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(3)))
					.setText("" + ((Cita) clase).getId_vehiculo());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(4)))
					.setText("" + ((Cita) clase).getId_taller());

		}
		if (clase instanceof Cliente) {
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(0)))
					.setText("" + ((Cliente) clase).getDni());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(1)))
					.setText("" + ((Cliente) clase).getNombre());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(2)))
					.setText("" + ((Cliente) clase).getApellidos());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(3)))
					.setText("" + ((Cliente) clase).getTelefono());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(4)))
					.setText("" + ((Cliente) clase).getDireccion());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(5)))
					.setText("" + ((Cliente) clase).getId_usuario());

		}
		if (clase instanceof Usuario) {
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(0)))
					.setText("" + ((Usuario) clase).getUsuario());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(1)))
					.setText("" + ((Usuario) clase).getClave());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(2)))
					.setText("" + ((Usuario) clase).getToken());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(3)))
					.setText("" + ((Usuario) clase).getFecha_inicio());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(4)))
					.setText("" + ((Usuario) clase).getFecha_fin());

		}
		if (clase instanceof Vehiculo_Tipo) {
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(0)))
					.setText("" + ((Vehiculo_Tipo) clase).getId());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(1)))
					.setText("" + ((Vehiculo_Tipo) clase).getDescripcion());

		}
		if (clase instanceof Vehiculo) {
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(0)))
					.setText("" + ((Vehiculo) clase).getMatricula());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(1)))
					.setText("" + ((Vehiculo) clase).getMarca());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(2)))
					.setText("" + ((Vehiculo) clase).getModelo());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(3)))
					.setText("" + ((Vehiculo) clase).getAnno());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(4)))
					.setText("" + ((Vehiculo) clase).getColor());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(5)))
					.setText("" + ((Vehiculo) clase).getId_cliente());
			((JFormattedTextField) componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(6)))
					.setText("" + ((Vehiculo) clase).getId_vehiculo_tipo());

		}

		EditorController controlador = new EditorController(componentesEditor, clase);

		btnEditorCancelar.addActionListener(controlador);
		btnEditorInsertar.addActionListener(controlador);

	}

}
