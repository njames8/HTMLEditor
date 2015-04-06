/**
 * 
 */
package ui;
import formatting.LineNumberAssistant;

import java.awt.Font;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cmd.SaveFile;
import files.HTMLFile;
import formatting.BaseTag;

/**
 * Represents a tab in the UI
 * 
 * @author Nick James
 * @author Jesse Roux
 *
 */
@SuppressWarnings("serial")
public class Tab extends ObservableTab implements DocumentListener  {
	/**
	 * The html file to be edited
	 */
	private HTMLFile file;
	
	public BaseTag head;
	/**
	 * Is this tab the focus of the window?
	 */
	private boolean focus;
	
	private LineNumberAssistant helper;

	/**
	 * Constructs a tab with a new file
	 */
	public Tab() {
		super();
		this.file = new HTMLFile();
		this.getDocument().addDocumentListener(this);
		this.helper = new LineNumberAssistant();
		this.attachObserver(helper);
	}
	
	public int getCaretLineNumber() {
		return this.helper.convertCharNumToLineNum(this.getCaretPosition());
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
		this.helper = new LineNumberAssistant();
		this.attachObserver(helper);
	}
	
	/**
	 * Update the file when text inserted
	 */
	public void insertUpdate(DocumentEvent e) {
		if(!file.getNeedsSave()) {
			file.changed();
		}
		notifyObservers();
    }
	
	/**
	 * if the update has been removed, the file doesn't need to be saved
	 */
    public void removeUpdate(DocumentEvent e) {
    	if(!file.getNeedsSave()) {
			file.changed();
			notifyObservers();
		}
    }

    /**
     * close the tab
     * @return - true if successfully closed, false otherwise
     */
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
	
	/**
	 * saves the file in this tab
	 * @return - true if save successfully, false otherwise
	 */
	public boolean saveFile() {
		boolean saved = file.save(this.getText());
		
		if(saved)
			notifyObservers();
		
		return saved;
	}

	/**
	 * Gets the file in this tab
	 * @return the file in this tab
	 */
	public HTMLFile getFile() {
		return file;
	}
	/**
	 * is this tab in the focus of the user?
	 * @return - true if in focus, false if out of focus
	 */
	public boolean getFocus() {
		return focus;
	}
	
	/**
	 * sets the focus of this tab
	 * @param b - true if in focus, false otherwise
	 */
	public void setFocus(boolean b) {
		focus = b;
	}
	
	/**
	 * gets the title of this tab
	 * @return - title of this tab
	 */
	public String getTitle() {
		return (file.getNeedsSave() ? "* " : "") + file.getFileName();
	}
	
	/**
	 * notifies the observers of changes
	 */
	public void notifyObservers() {
		for(Observer o : obs) {
			o.update(this);
		}
	}
	
	/**
	 * saves the file as ...
	 * @param path - the path to the file
	 * @param text - the text to be saved to the file
	 */
	public void saveAs(String path, String text) {
		if(file.saveAs(path, text)) {
			notifyObservers();
		}
	}
	
	/**
	 * Do something if the update has changed
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Switches the setting for auto-indentation for the current tab
	 * @author John Mullen
	 */
	public void setAutoIndent() {
		head.setAutoIndent(!head.getAutoIndent());
	}
}
