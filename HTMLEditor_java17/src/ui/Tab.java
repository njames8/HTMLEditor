/**
 * 
 */
package ui;

import java.awt.Font;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cmd.SaveFile;
import files.HTMLFile;

/**
 * Represents a tab in the UI
 * 
 * @author nick
 *
 */
@SuppressWarnings("serial")
public class Tab extends ObservableTab implements DocumentListener  {
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
	public Tab(String title) {
		super();
		this.file = new HTMLFile();
		
		this.getDocument().addDocumentListener(this);
	}

	/**
	 * Constructs a tab with an opened file
	 * 
	 * @param file
	 *            - file to be edited
	 * @param text
	 *            - the text
	 */
	public Tab(HTMLFile file, String text) {
		super();
		this.file = file;
		this.setText(text);
		
		// Word wrapping
		this.setWrapStyleWord(true);
		this.setLineWrap(true);
		
		// FIXME: font doesnt work
		this.setFont(new Font("Consolas", Font.PLAIN, 11));
		
		this.getDocument().addDocumentListener(this);
	}
	
	public void insertUpdate(DocumentEvent e) {
		if(!file.getNeedsSave()) {
			file.changed();
			notifyObservers();
		}
    }
	
    public void removeUpdate(DocumentEvent e) {
    	if(!file.getNeedsSave()) {
			file.changed();
			notifyObservers();
		}
    }

	public boolean close() {
		// TODO close tab method
		if (file.getNeedsSave()) {
			int reply = JOptionPane
					.showConfirmDialog(
							null,
							"Would you like to save changes to file: "
									+ file.getFileName(), "Save Changes?",
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
		boolean saved = file.save(this.getText());
		
		if(saved)
			notifyObservers();
		
		return saved;
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
	
	public String getTitle() {
		return (file.getNeedsSave() ? "* " : "") + file.getFileName();
	}
	
	public void notifyObservers() {
		for(Observer o : obs) {
			o.update(this);
		}
	}
	
	public void saveAs(String path, String text) {
		if(file.saveAs(path, text)) {
			notifyObservers();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
