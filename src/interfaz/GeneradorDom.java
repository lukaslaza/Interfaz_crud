package interfaz;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import clases.dbConexion;
import controller.PrincipalController;
import database.funciones;

public class GeneradorDom {
	private Document document;
	private Element row = null;
	private Element node = null;
	private Object value = null;

	public GeneradorDom() throws ParserConfigurationException {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		document = builder.newDocument();

	}

	public void generarDocument(PrincipalController controladorPrincipal,
			HashMap<String, Component> componentesPrincipal) throws SQLException, ClassNotFoundException {

		Element results = document.createElement("Talleres");
		document.appendChild(results);

		Connection con = dbConexion.getConnection();

		ResultSet rs = con.createStatement()
				.executeQuery(funciones.sentenciaFiltros(controladorPrincipal, componentesPrincipal));

		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();

		while (rs.next()) {
			row = document.createElement(Principal.getClaseActual().toUpperCase());
			results.appendChild(row);
			for (int i = 1; i <= colCount; i++) {
				String columnName = rsmd.getColumnName(i);
				value = rs.getObject(i);
				node = document.createElement(columnName);
				node.appendChild(document.createTextNode(value + ""));
				row.appendChild(node);
			}
		}

	}

	public void generarXml() throws TransformerException, IOException {
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transformer = factoria.newTransformer();

		Source source = new DOMSource(document);
		File file = new File("C:\\Users\\Asus-Lukas\\Desktop\\EXPORTADO.xml");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		Result result = new StreamResult(pw);

		transformer.transform(source, result);
	}
}
