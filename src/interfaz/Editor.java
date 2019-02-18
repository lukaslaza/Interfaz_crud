package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.EditorController;
import database.funciones;
import net.miginfocom.swing.MigLayout;

public class Editor {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor();
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
	public Editor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		HashMap<String, Component> componentesEditor = new HashMap<String, Component>();

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 240, 401);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		JPanel titulo = new JPanel();
		frame.getContentPane().add(titulo, BorderLayout.NORTH);

		JLabel lblClase = new JLabel(Principal.getClaseActual().toUpperCase());
		lblClase.setFont(new Font("Verdana", Font.BOLD, 25));
		lblClase.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.add(lblClase);

		JPanel botones = new JPanel();
		frame.getContentPane().add(botones, BorderLayout.SOUTH);

		JButton btnEditorInsertar = new JButton("Insertar");
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
			componentesEditor.put("txtEditor" + funciones.getMetadatosTablaArray().get(i), new JTextField());

			formulario.add(componentesEditor.get("lblEditor" + funciones.getMetadatosTablaArray().get(i)),
					"cell 0 " + i + ",growx,aligny center");
			formulario.add(componentesEditor.get("txtEditor" + funciones.getMetadatosTablaArray().get(i)),
					"cell 1 " + i + ",growx,aligny center");

		}

		EditorController controlador = new EditorController(componentesEditor);

		btnEditorCancelar.addActionListener(controlador);
		btnEditorInsertar.addActionListener(controlador);

	}

}
