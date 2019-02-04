package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ControlPrueba implements ActionListener {

	JTextField txt1;
	JTextField txt2;
	JFrame frame;
	
	public ControlPrueba(JFrame frame, JTextField t1, JTextField t2) {
		// TODO Auto-generated constructor stub
		txt1 = t1;
		txt2 = t2;
		
		this.frame=frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Pulsado");
		
		txt2.setText(txt1.getText());
		frame.;
	}

}
