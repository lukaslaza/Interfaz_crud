package interfaz;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import controller.CreadorController;
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
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

public class Creador {
	private static Creador instancia = null;

	public static Creador getInstance() {
		if (instancia == null) {
			instancia = new Creador();
		}
		return instancia;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Creador window = new Creador();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Creador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		HashMap<String, Component> componentesCreador = new HashMap<String, Component>();

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 240, 401);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		componentesCreador.put("frame", frame);

		JPanel titulo = new JPanel();
		frame.getContentPane().add(titulo, BorderLayout.NORTH);

		JLabel lblClase = new JLabel(Principal.getClaseActual().toUpperCase());
		lblClase.setFont(new Font("Verdana", Font.BOLD, 25));
		lblClase.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.add(lblClase);

		JPanel botones = new JPanel();
		frame.getContentPane().add(botones, BorderLayout.SOUTH);

		JButton btnCreadorInsertar = new JButton("Insertar");
		JButton btnCreadorCancelar = new JButton("Cancelar");

		btnCreadorInsertar.setActionCommand("InsertarCreador");
		btnCreadorCancelar.setActionCommand("CancelarCreador");

		componentesCreador.put("btnCreadorCancelar", btnCreadorCancelar);
		componentesCreador.put("btnCreadorInsertar", btnCreadorInsertar);
		componentesCreador.put("frame", frame);

		botones.add(btnCreadorInsertar);
		botones.add(btnCreadorCancelar);

		JPanel formulario = new JPanel();
		frame.getContentPane().add(formulario, BorderLayout.CENTER);

		formulario.setLayout(new MigLayout("", "[89px][89px]", "[87px][87px]"));
		
		for (int j = 0; j < funciones.getMetadatosTablaArrayType().size(); j++) {
			System.out.println(funciones.getMetadatosTablaArrayType().get(j));
		}

		for (int i = 0; i < funciones.getMetadatosTablaArray().size() - 1; i++) {

			//STRING
			//1 --> string
			//12 --> big string
			if (funciones.getMetadatosTablaArrayType().get(i) == 1
					|| funciones.getMetadatosTablaArrayType().get(i) == 12) {
				componentesCreador.put("lblCreador" + funciones.getMetadatosTablaArray().get(i),
						new JLabel(funciones.getMetadatosTablaArray().get(i) + " -->"));
				componentesCreador.put("txtCreador" + funciones.getMetadatosTablaArray().get(i),
						new JFormattedTextField(new String()));
			}
			//INTEGER
			//-6 --> small int
			if (funciones.getMetadatosTablaArrayType().get(i) == 2
					|| funciones.getMetadatosTablaArrayType().get(i) == 4 || funciones.getMetadatosTablaArrayType().get(i) == -6) {
				componentesCreador.put("lblCreador" + funciones.getMetadatosTablaArray().get(i),
						new JLabel(funciones.getMetadatosTablaArray().get(i) + " -->"));
				componentesCreador.put("txtCreador" + funciones.getMetadatosTablaArray().get(i),
						new JFormattedTextField(new Integer(2000)));
			}
			//FECHA
			if (funciones.getMetadatosTablaArrayType().get(i) == 91) {
				componentesCreador.put("lblCreador" + funciones.getMetadatosTablaArray().get(i),
						new JLabel(funciones.getMetadatosTablaArray().get(i) + " -->"));
				componentesCreador.put("txtCreador" + funciones.getMetadatosTablaArray().get(i),
						new JFormattedTextField(new Date().toString()));
			}
			//FECHA2
			if (funciones.getMetadatosTablaArrayType().get(i) == 93) {
				componentesCreador.put("lblCreador" + funciones.getMetadatosTablaArray().get(i),
						new JLabel(funciones.getMetadatosTablaArray().get(i) + " -->"));
				componentesCreador.put("txtCreador" + funciones.getMetadatosTablaArray().get(i),
						new JFormattedTextField());
			}
			//Hora
			if (funciones.getMetadatosTablaArrayType().get(i) == 92) {
				componentesCreador.put("lblCreador" + funciones.getMetadatosTablaArray().get(i),
						new JLabel(funciones.getMetadatosTablaArray().get(i) + " -->"));
				componentesCreador.put("txtCreador" + funciones.getMetadatosTablaArray().get(i),
						new JFormattedTextField());
			}
			//Float
			if (funciones.getMetadatosTablaArrayType().get(i) == 7) {
				componentesCreador.put("lblCreador" + funciones.getMetadatosTablaArray().get(i),
						new JLabel(funciones.getMetadatosTablaArray().get(i) + " -->"));
				componentesCreador.put("txtCreador" + funciones.getMetadatosTablaArray().get(i),
						new JFormattedTextField());
			}
			formulario.add(componentesCreador.get("lblCreador" + funciones.getMetadatosTablaArray().get(i)),
					"cell 0 " + i + ",growx,aligny center");
			formulario.add(componentesCreador.get("txtCreador" + funciones.getMetadatosTablaArray().get(i)),
					"cell 1 " + i + ",growx,aligny center");

		}

		CreadorController controlador = new CreadorController(componentesCreador);

		btnCreadorCancelar.addActionListener(controlador);
		btnCreadorInsertar.addActionListener(controlador);

	}

}
