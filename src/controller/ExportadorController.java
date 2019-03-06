package controller;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import clases.Cita;
import clases.Cliente;
import clases.Taller;
import clases.Usuario;
import clases.Vehiculo;
import clases.Vehiculo_Tipo;
import clases.dbConexion;
import database.funciones;
import funciones.funcionesError;
import interfaz.Principal;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

		if (((JRadioButton) componentesExportador.get("rdbtTiposVehiculos")).isSelected() == false
				&& ((JRadioButton) componentesExportador.get("rdbtnCitas")).isSelected() == false
				&& ((JRadioButton) componentesExportador.get("rdbtnTalleres")).isSelected() == false
				&& ((JRadioButton) componentesExportador.get("rdbtnVehiculos")).isSelected() == false
				&& ((JRadioButton) componentesExportador.get("rdbtnUsuarios")).isSelected() == false
				&& ((JRadioButton) componentesExportador.get("rdbtnClientes")).isSelected() == false) {
			funcionesError.error_msg(((Frame) componentesExportador.get("frame")), "No hay nada que exportar",
					"No hay datos que exportar");

		} else {
			// abrir archivo

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// elemento raiz
			Document doc = docBuilder.newDocument();

			if (((JRadioButton) componentesExportador.get("rdbtTiposVehiculos")).isSelected() == true) {
				exportadorTiposVehiculos(doc);
			}
			if (((JRadioButton) componentesExportador.get("rdbtnCitas")).isSelected() == true) {
				exportadorCitas();
			}
			if (((JRadioButton) componentesExportador.get("rdbtnTalleres")).isSelected() == true) {
				exportadorTalleres();
			}
			if (((JRadioButton) componentesExportador.get("rdbtnVehiculos")).isSelected() == true) {
				exportadorVehiculos();
			}
			if (((JRadioButton) componentesExportador.get("rdbtnUsuarios")).isSelected() == true) {
				exportadorUsuarios();
			}
			if (((JRadioButton) componentesExportador.get("rdbtnClientes")).isSelected() == true) {
				exportadorClientes(doc);
			}

			// Cerrar archivo

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(ruta));
			// StreamResult result = new StreamResult(new File("archivo.xml"));

			// Si se quiere mostrar por la consola...
			// StreamResult result = new StreamResult(System.out);

			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				e.printStackTrace();
			}

		}

	}

	private static void exportadorUsuarios() {
		ResultSet rs = null;
	    Statement stmt = null;
	    String sql;

	    try {
	      DocumentBuilderFactory factory = 
	         DocumentBuilderFactory.newInstance();
	      DocumentBuilder builder =factory.newDocumentBuilder();
	      Document doc = builder.newDocument();
	      Element results = doc.createElement("Results");
	      doc.appendChild(results);

	      // connection to an ACCESS MDB
	      Connection conn = dbConexion.getConnection();

	      sql =  "select * from usuario";
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery(sql);

	      ResultSetMetaData rsmd = rs.getMetaData();
	      int colCount = rsmd.getColumnCount();

	      while (rs.next()) {
	        Element row = doc.createElement("usuario");
	        results.appendChild(row);
	        for (int ii = 1; ii <= colCount; ii++) {
	           String columnName = rsmd.getColumnName(ii);
	           Object value = rs.getObject(ii);
	           Element node = doc.createElement(columnName);
	           node.appendChild(doc.createTextNode(""+value.toString()));
	           row.appendChild(node);
	        }
	      }

	      System.out.println(getDocumentAsXml(doc));

	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	      try {
	        if (stmt != null) stmt.close();
	        if (rs != null) rs.close();
	      }
	      catch (Exception e) {
	      }
	    }

	}

	private static void exportadorVehiculos() {
		// TODO Auto-generated method stub

	}

	private static void exportadorTalleres() {
		// TODO Auto-generated method stub

	}

	private static void exportadorCitas() {
		// TODO Auto-generated method stub

	}

	private static void exportadorTiposVehiculos(Document doc) {
		String query = "SELECT * FROM vehiculo_tipo ";
		// COnexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Vehiculo_Tipo> clases = new ArrayList<Vehiculo_Tipo>();
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				clases.add(new Vehiculo_Tipo(rs.getString("id"), rs.getString("descripcion")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Crear XML
		Element vehiculo_tipos = doc.createElement("vehiculo_tipos");
		doc.appendChild(vehiculo_tipos);

		for (int i = 0; i < clases.size(); i++) {

			// empleado
			Element vehiculo_tipo = doc.createElement("vehiculo_tipo");
			vehiculo_tipos.appendChild(vehiculo_tipo);

			// Atributo para distinguir tipos
			Attr attr = doc.createAttribute("id");
			attr.setValue(clases.get(i).getId());
			vehiculo_tipo.setAttributeNode(attr);

			// id
			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(clases.get(i).getId()));
			vehiculo_tipo.appendChild(id);

			// descripcion
			Element descripcion = doc.createElement("descripcion");
			descripcion.appendChild(doc.createTextNode(" "+clases.get(i).getDescripcion()));
			vehiculo_tipo.appendChild(descripcion);

		}
	}

	public static void exportadorClientes(Document doc) {
		String query = "SELECT * FROM cliente ";
		// COnexion
		Connection conn = (Connection) dbConexion.getConnection();
		// Arraylist donde guardaremos el Resulset
		ArrayList<Cliente> clases = new ArrayList<Cliente>();
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException errorStmt) {
			errorStmt.printStackTrace();
		}

		try {
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			while (rs.next()) {
				clases.add(new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("telefono"), rs.getString("direccion"), rs.getString("id_usuario")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Element clientes = doc.createElement("clientes");
		doc.appendChild(clientes);

		for (int i = 0; i < clases.size(); i++) {

			// empleado
			Element cliente = doc.createElement("cliente");
			clientes.appendChild(cliente);

			// Atributo para distinguir empleados
			Attr attr = doc.createAttribute("id");
			attr.setValue(clases.get(i).getDni());
			cliente.setAttributeNode(attr);

			// dni
			Element dni = doc.createElement("dni");
			dni.appendChild(doc.createTextNode(clases.get(i).getDni()));
			cliente.appendChild(dni);

			// nombre
			Element nombre = doc.createElement("nombre");
			nombre.appendChild(doc.createTextNode(clases.get(i).getNombre()));
			cliente.appendChild(nombre);

			// apellidos
			Element apellidos = doc.createElement("appellidos");
			apellidos.appendChild(doc.createTextNode(clases.get(i).getApellidos()));
			cliente.appendChild(apellidos);

			// telefono
			Element telefono = doc.createElement("telefono");
			telefono.appendChild(doc.createTextNode(clases.get(i).getTelefono()));
			cliente.appendChild(telefono);

			// direccion
			Element direccion = doc.createElement("direccion");
			direccion.appendChild(doc.createTextNode(clases.get(i).getDireccion()));
			cliente.appendChild(direccion);

			// Id_usuario
			Element id_usuario = doc.createElement("id_usuario");
			id_usuario.appendChild(doc.createTextNode(clases.get(i).getId_usuario()));
			cliente.appendChild(id_usuario);
		}
	}

	
	 public static String getDocumentAsXml(Document doc)
		      throws TransformerConfigurationException, TransformerException {
		    DOMSource domSource = new DOMSource(doc);
		    TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer transformer = tf.newTransformer();
		    //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
		    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
		    // we want to pretty format the XML output
		    // note : this is broken in jdk1.5 beta!
		    transformer.setOutputProperty
		       ("{http://xml.apache.org/xslt}indent-amount", "4");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    //
		    java.io.StringWriter sw = new java.io.StringWriter();
		    StreamResult sr = new StreamResult(sw);
		    transformer.transform(domSource, sr);
		    return sw.toString();
		 }
	
	public static void main(String[] args) {
exportadorUsuarios();
	}

}
