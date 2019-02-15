package controller;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import funciones.funcionesError;

public class ExportadorController implements ActionListener {

	// Clases
	private HashMap<String, Component> componentesExportador;

	// Constructor
	public ExportadorController(HashMap<String, Component> componentes) {
		this.componentesExportador = componentes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();
		if (comando == "Aceptar") {
			if (((JTextField) componentesExportador.get("txtRuta")).getText() == null
					|| ((JTextField) componentesExportador.get("txtRuta")).getText() == "C:\\") {
				funcionesError.error_msg((Frame) componentesExportador.get("frame"), "La ruta especifaca no es valida",
						"Ruta Invalida");
			} else {
				exportarDatos();
			}
		}
		if (comando == "Cancelar") {
			((Window) componentesExportador.get("frame")).dispose();
		}
		if (comando.equalsIgnoreCase("Chooser")) {
			chooser((JTextField) componentesExportador.get("txtRuta"));
		}

	}

	/**
	 * Metodo que abre una ventana de tipo chooser para poder operar con el archivo
	 */
	private void chooser(JTextField label) {
		try {
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int seleccion = fileChooser.showOpenDialog(label);

			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = fileChooser.getSelectedFile();
				label.setText(fichero.getAbsolutePath());
			}
		} catch (Exception e) {
			funcionesError.error_msg((Frame) componentesExportador.get("frame"),
					"Ha ocurrido un error al intentar abrir la carpeta", "ERROR AL ABRIR LA CARPETA");
		}

	}

	private void exportarDatos() {
		String ruta = ((JTextComponent) componentesExportador.get("txtRuta")).getText();
		ruta = ruta + "\\exportado.txt";
		System.out.println(ruta);
		try {
			FileWriter archivo = new FileWriter(ruta);
			//TODO ver que botones esten selecionados y escribir los datos
			
			/*
			 * <Clase>
			 * <numRegistro>
			 * <datos>
			 * </datos>
			 * </numRegistro>
			 * </Clase>
			 * 
			 */
			
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
