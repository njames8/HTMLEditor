package ui;

import javax.swing.JOptionPane;
/*
 * 
 * DO WE NEED THIS???
 *
 */
public class MessageBox extends JOptionPane {
	
	public MessageBox(String title, String msg, int msgType) {
		JOptionPane.showMessageDialog(null, msg, title, msgType);
	}
}
