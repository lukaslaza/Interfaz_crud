package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ExportadorController implements ActionListener {

	// Clases
	JTextField label;

	// Constructor
	public ExportadorController(JTextField label) {
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
		if (comando == "Aceptar") {
			System.out.println("Aceptar");
		}
		if (comando == "Cancelar") {
			System.out.println("Cancelar");
		}
		if (comando.equalsIgnoreCase("Chooser")) {
			chooser(label);
		}

	}

	/**
	 * Metodo que abre una ventana de tipo chooser para poder operar con el archivo
	 */
	public void chooser(JTextField label) {
		try {
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int seleccion = fileChooser.showOpenDialog(label);

			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = fileChooser.getSelectedFile();
				label.setText(fichero.getAbsolutePath());
			}
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al intentar abrir la carpeta");
		}

	}

}
