package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONObject;

import funciones.funcionesError;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.Period;
import java.util.LinkedHashMap;
import java.util.Map;

import interfaz.Login;
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
				funcionesError.error_msg(frame, "Los campos no pueden estar vacios", "Campos en blanco");
				vaciarCampos();
			} else {

				// TODO comprobar que los datos pasados por parametros no estan vacios o tengan
				// signos, etc
				try {
					peticion(userName, userpass);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Comprobar login con los web services
				try {
					if (peticion(userName, userpass)==true) {
						frame.dispose();
						new Principal();
					} else {
						funcionesError.error_msg(frame, "Usuario y/o Contraseña no validos", "Acceso no autorizado");
						// Establecemos los valores en vacios
						vaciarCampos();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
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

	public static boolean peticion(String user, String clave) throws IOException {
		URL url = new URL("http://andresterol.int.elcampico.org/taller/login");
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("usuario", user);
		params.put("clave", clave);

		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);

		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer content = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		JSONObject response = new JSONObject(content.toString());

		if (response.getBoolean("estado")) {
			return true;
		}
		return false;

	}

	public static void main(String[] args) {
		new Login();
	}

}
