package ui;

import javax.swing.JOptionPane;

/**
 * The message box to display to user
 * @author Adam Walsh
 *
 */
@SuppressWarnings("serial")
public class MessageBox extends JOptionPane {
	
	/**
	 * constructor
	 * @param title - title of message
	 * @param msg - message
	 * @param msgType - the type of dialog box to display
	 */
	public MessageBox(String title, String msg, int msgType) {
		JOptionPane.showMessageDialog(null, msg, title, msgType);
	}
}
