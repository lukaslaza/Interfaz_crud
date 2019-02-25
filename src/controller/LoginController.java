package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import funciones.funcionesError;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Period;

import interfaz.Principal;

public class LoginController implements ActionListener, KeyListener {

	JPasswordField contra;
	JTextField user;
	JFrame frame;

	public LoginController(JFrame frame, JTextField user, JPasswordField contra) {
		this.contra = contra;
		this.user = user;
		this.frame = frame;
	}


	// TODO implementar que se comprubee con la api si el usuario es correcto
	@Override
	public void actionPerformed(ActionEvent e) {
		// Accion
		String comando = e.getActionCommand();

		// Variables
		String userName = user.getText().trim();
		String userpass = String.valueOf(contra.getPassword()).trim();

		// Acion escogida
		if (comando.equalsIgnoreCase("Acceder")) {
			// Si algun campo esta vacio lanzamos error y borramos valores de los campos
			if (userName.isEmpty() || userpass.isEmpty()) {
				funcionesError.error_msg(frame,"Los campos no pueden estar vacios", "Campos en blanco");
				vaciarCampos();
			} else {

				// TODO comprobar que los datos pasados por parametros no estan vacios o tengan
				// signos, etc

				// Comprobar login con los web services
				if (userName.equals("lukas") && userpass.equals("laza")) {
					// Cerramos la ventana de login
					frame.dispose();
					// Creamos la ventana principal
					new Principal();
				} else {
					// Lanzamos mensaje de error
					funcionesError.error_msg(frame,"Usuario y/o Contraseña no validos", "Acceso no autorizado");
					// Establecemos los valores en vacios
					vaciarCampos();

				}
			}
		}
	}

	public void vaciarCampos() {
		user.setText("");
		contra.setText("");
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ENTER) {
			System.out.println("Presionó Enter!" + (char) key);
			frame.dispose();
		}

	}
	
	private static void peticion() {
	     URL url;
	        try {
	            // Creando un objeto URL
	            url = new URL("http://andresterol.int.elcampico.org/taller/login:8080");
	 
	            // Realizando la petición GET
	            URLConnection con = url.openConnection();
	 
	            // Leyendo el resultado
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    con.getInputStream()));
	 
	            String linea;
	            while ((linea = in.readLine()) != null) {
	                System.out.println(linea);
	            }
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	public static void main(String[] args) {
		peticion();
	}

}
