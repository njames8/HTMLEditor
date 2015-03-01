package ui;

import javax.swing.JOptionPane;

public class AlertMessageBox extends MessageBox {
	
	public AlertMessageBox(String title, String msg) {
		super(title, msg, JOptionPane.WARNING_MESSAGE);
	}
}
