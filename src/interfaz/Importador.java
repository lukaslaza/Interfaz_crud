package interfaz;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import java.awt.Font;
import java.io.File;
import java.util.HashMap;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ImportadorController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Importador {

	private JFrame frame;
	private JTextField txtRuta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Importador window = new Importador();
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
	public Importador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		HashMap<String, Component> componentesImportador = new HashMap<String, Component>();
		
		
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 197);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		componentesImportador.put("frame", frame);
		
		JLabel lblTitulo = new JLabel("IMPORTAR");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTitulo.setBounds(0, 11, 434, 33);
		frame.getContentPane().add(lblTitulo);
		componentesImportador.put("lblTitulo", lblTitulo);
		
		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setBounds(10, 79, 46, 14);
		frame.getContentPane().add(lblRuta);
		componentesImportador.put("lblRuta", lblRuta);
		
		JTextField txtRuta = new JTextField();
		txtRuta.setText("C:\\");
		txtRuta.setBounds(69, 76, 323, 20);
		frame.getContentPane().add(txtRuta);
		txtRuta.setColumns(10);
		componentesImportador.put("txtRuta", txtRuta);
		
		ImportadorController controlador= new ImportadorController(componentesImportador);
		
		JLabel lblSuperior = new JLabel("\u00BFD\u00F3nde se encuentra el XML?");
		lblSuperior.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuperior.setBounds(10, 55, 414, 14);
		frame.getContentPane().add(lblSuperior);
		componentesImportador.put("lblSuperior", lblSuperior);
			

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(236, 125, 89, 23);
		componentesImportador.put("btnCancelar", btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(335, 125, 89, 23);
		componentesImportador.put("btnAceptar", btnAceptar);
		
		JButton btnChooser = new JButton("...");
		btnChooser.setBounds(402, 76, 22, 21);

		componentesImportador.put("btnChooser", btnChooser);
		
		
		frame.getContentPane().add(btnCancelar);
		frame.getContentPane().add(btnChooser);
		frame.getContentPane().add(btnAceptar);
		
		btnChooser.setActionCommand("Chooser");
		btnCancelar.setActionCommand("Cancelar");
		btnAceptar.setActionCommand("Aceptar");
		
		btnChooser.addActionListener(controlador);
		btnAceptar.addActionListener(controlador);
		btnCancelar.addActionListener(controlador);
				
	}
	

}
