package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.LoginController;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frame;
	private JTextField txUsuario;
	private JTextField txContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JLabel lblLoginTaller = new JLabel("LOGIN TALLER");
		JTextField txUsuario = new JTextField();
		JLabel lblUsuario = new JLabel("Usuario");
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a");
		JButton btnAcceder = new JButton("Acceder");
		JPasswordField txContrasena = new JPasswordField();

		frame = new JFrame();
		
		LoginController controlador=new LoginController(frame,txUsuario,txContrasena);
		
		frame.setBounds(100, 100, 275, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		lblLoginTaller.setFont(new Font("Verdana", Font.BOLD, 18));

		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginTaller.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		btnAcceder.setBounds(31, 165, 202, 29);
		txContrasena.setBounds(31, 122, 202, 20);
		txUsuario.setBounds(31, 76, 202, 20);
		lblLoginTaller.setBounds(0, 10, 259, 23);
		lblNewLabel.setBounds(31, 102, 202, 14);
		lblUsuario.setBounds(31, 56, 202, 14);

		txContrasena.setColumns(10);
		txUsuario.setColumns(5);

		frame.getContentPane().add(btnAcceder);
		frame.getContentPane().add(txUsuario);
		frame.getContentPane().add(lblLoginTaller);
		frame.getContentPane().add(txContrasena);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(lblUsuario);
		
		btnAcceder.setActionCommand("acceder");
		btnAcceder.addActionListener(controlador);
		btnAcceder.addKeyListener(controlador);
		

	}
}
