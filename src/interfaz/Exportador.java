package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;

import controller.ExportadorController;

import java.awt.Font;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Exportador {

	private JFrame frame;
	private JTextField txtRuta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exportador window = new Exportador();
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
	public Exportador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JRadioButton rdbtTiposVehiculos = new JRadioButton("Tipos de vehiculos");
		JRadioButton rdbtnCitas = new JRadioButton("Citas");
		JRadioButton rdbtnTalleres = new JRadioButton("Talleres");
		JRadioButton rdbtnVehiculos = new JRadioButton("Vehiculos");
		JRadioButton rdbtnUsuarios = new JRadioButton("Usuarios");
		JRadioButton rdbtnClientes = new JRadioButton("Clientes");

		JButton btnChooser = new JButton("...");
		JButton btnCancelar = new JButton("Cancelar");
		JButton btnAceptar = new JButton("Aceptar");

		JTextField txtRuta = new JTextField();

		JLabel lblTitulo = new JLabel("EXPORTAR DATOS");
		JLabel lblRuta = new JLabel("Ruta:");

		JPanel panel = new JPanel();

		//Controllador
		ExportadorController ExportController = new ExportadorController(txtRuta);

		frame = new JFrame();
		frame.setBounds(100, 100, 460, 340);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		panel.setLayout(null);

		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 11, 444, 39);
		panel.add(lblTitulo);

		rdbtTiposVehiculos.setBounds(18, 238, 152, 23);
		rdbtnClientes.setBounds(18, 264, 109, 23);
		rdbtnCitas.setBounds(18, 134, 109, 23);
		rdbtnTalleres.setBounds(18, 160, 109, 23);
		rdbtnVehiculos.setBounds(18, 186, 109, 23);
		rdbtnUsuarios.setBounds(18, 212, 126, 23);

		panel.add(rdbtnUsuarios);
		panel.add(rdbtTiposVehiculos);
		panel.add(rdbtnVehiculos);
		panel.add(rdbtnTalleres);
		panel.add(rdbtnCitas);
		panel.add(rdbtnClientes);

		txtRuta.setText("C:\\");
		txtRuta.setBounds(74, 78, 335, 20);
		txtRuta.setColumns(10);
		panel.add(txtRuta);

		btnAceptar.setBounds(229, 212, 180, 75);
		panel.add(btnAceptar);
		btnAceptar.setActionCommand("Aceptar");
		btnAceptar.addActionListener(ExportController);

		btnCancelar.setBounds(229, 109, 180, 75);
		panel.add(btnCancelar);
		btnCancelar.setActionCommand("Cancelar");
		btnCancelar.addActionListener(ExportController);

		lblRuta.setBounds(18, 81, 46, 14);
		panel.add(lblRuta);

		JLabel lblTexto = new JLabel("Que quieres exportar:");
		lblTexto.setBounds(18, 113, 126, 14);
		panel.add(lblTexto);

		JLabel lblTextoSup = new JLabel("\u00BFDonde quieres guardar el XML?");
		lblTextoSup.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextoSup.setBounds(10, 61, 424, 14);
		panel.add(lblTextoSup);

		btnChooser.setBounds(414, 77, 20, 21);
		panel.add(btnChooser);
		btnChooser.setActionCommand("Chooser");
		btnChooser.addActionListener(ExportController);

	}
}
