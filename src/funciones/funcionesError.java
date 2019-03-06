package funciones;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class funcionesError {

	/**
	 * Lanza una ventana de error
	 * @param msg
	 * @param msgSup
	 */
	public static void error_msg(Frame fr, String msg, String msgSup) {
		JOptionPane.showMessageDialog(fr, msg, msgSup, JOptionPane.ERROR_MESSAGE);
	}
	public static void aviso_msg(Frame fr, String msg, String msgSup) {
		JOptionPane.showMessageDialog(fr, msg, msgSup, JOptionPane.OK_OPTION);
	}
	
}
