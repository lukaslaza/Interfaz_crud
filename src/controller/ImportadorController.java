package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ImportadorController implements ActionListener {

	//Clases
	
	JTextField label;

	
	//Constructor
	public ImportadorController(JTextField label) {
		this.label = label;
	}

	//Action
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();

		if (comando.equalsIgnoreCase("Aceptar")) {
			
			//importxml(File fixheroXml);
			//cerrarventanta si ha funcionado, si no, lanzar error.
		}
		if (comando.equalsIgnoreCase("cancelar")) {
			try {
			//Cerrar ventana.
			} catch (Exception e1) {
				System.out.println("Ha ocurrido un error");
			}
		}
		if (comando.equalsIgnoreCase("chooser")) {
				chooser(label);
		}

	}

	
	/**
	 * Metodo que abre una ventana de tipo chooser para poder operar con el archivo
	 */
	public void chooser(JTextField label) {
		try {
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			int seleccion = fileChooser.showOpenDialog(label);

			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = fileChooser.getSelectedFile();
				label.setText(fichero.getAbsolutePath());
			}
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al intentar abrir el archivo");
		}

	}
}
