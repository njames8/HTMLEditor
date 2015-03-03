/**
 * 
 */
package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Observable;

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
	
	private String title;

	/**
	 * Constructs a tab with a new file
	 */
	public Tab(String title) {
		super();
		this.file = new HTMLFile();
		this.title = title;
	}

	/**
	 * Constructs a tab with an opened file
	 * 
	 * @param file
	 *            - file to be edited
	 * @param j
	 *            - the area of tabs for this to be attached
	 */
	public Tab(HTMLFile file, String text) {
		super();
		this.file = file;
		this.title = file.GetTabName();
		this.setText(text);
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
				/*UpdateText t = new UpdateText((Tab) e.getSource(),
						(JTabbedPane) e.getComponent().getParent());
				t.execute();*/
				((Tab)e.getSource()).file.Changed();
			}

		});
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//if (arg0.getButton() == MouseEvent)
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	public boolean close() {
		// TODO close tab method
		if (file.GetNeedsSave()) {
			int reply = JOptionPane
					.showConfirmDialog(
							null,
							"Would you like to save changes to file: "
									+ file.GetFileName(), "Save Changes?",
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
		return file.Save(this.getText());
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
	
	public String GetTitle() {
		return title;
	}
}
