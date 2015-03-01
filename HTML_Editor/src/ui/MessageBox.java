package ui;

import javax.swing.JOptionPane;

public class MessageBox extends JOptionPane {
	
	public MessageBox(String title, String msg, int type) {
		JOptionPane.showMessageDialog(null, msg, title, type);
	}
}
