/**
 * 
 */
package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import cmd.CloseTab;
import cmd.SaveFile;
import cmd.UpdateText;
import files.HTMLFile;

/**
 * Represents a tab in the UI
 * 
 * @author nick
 *
 */
public class Tab extends JTextPane {
	/**
	 * The html file to be edited
	 */
	private HTMLFile file;

	/**
	 * Is this tab the focus of the window?
	 */
	private boolean focus;

	/**
	 * Constructs a tab with a new file
	 */
	public Tab() {
		super();
		this.file = new HTMLFile();
	}

	/**
	 * Constructs a tab with an opened file
	 * 
	 * @param file
	 *            - file to be edited
	 * @param j
	 *            - the area of tabs for this to be attached
	 */
	public Tab(HTMLFile file) {
		super();
		this.file = file;
		this.setText(file.getText());
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.isControlDown() && arg0.getKeyCode() == KeyEvent.VK_W) {
	                

	            }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				UpdateText t = new UpdateText((Tab) e.getSource(),
						(JTabbedPane) e.getComponent().getParent());
				t.execute();
			}

		});
	}

	public boolean close() {
		// TODO close tab method
		System.out.println(file.getNeedsToBeSaved());
		if (file.getNeedsToBeSaved()) {
			int reply = JOptionPane
					.showConfirmDialog(
							null,
							"Would you like to save changes to file: "
									+ file.getName(), "Save Changes?",
							JOptionPane.YES_NO_CANCEL_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				SaveFile s = new SaveFile(this);
				s.execute();
			}
			else if (reply == JOptionPane.CANCEL_OPTION){
				return false;
			}
		}
		return true;
	}
	
	public boolean saveFile() {
		return this.file.Save();
	}

	public HTMLFile getFile() {
		return file;
	}

	public boolean getFocus() {
		return focus;
	}

	public void setFocus(boolean b) {
		focus = b;
	}
}
