/**
 * 
 */
package cmd;
import java.io.IOException;

import ui.*;

import javax.swing.JFileChooser;

import files.HTMLFile;

/**
 * Represents a Save command
 * @author Nick James
 *
 */
public class SaveFile implements Command {
	/**
	 * The HTML file to save
	 */
	private HTMLFile file;
	
	/**
	 * The tab that holds the html file
	 */
	private Tab t;
	
	/**
	 * The application window
	 */
	private EditorWindow win;
	
	/**
	 * The SaveFile Constructor
	 * @param t - The current tab
	 * @param w - The application window
	 */
	public SaveFile(Tab t){
			this.file = t.getFile();
	
			this.t = t;
	}
	
	/**
	 * Executes the save command
	 */
	@Override
	public void execute() {
		file.Save( t);
	}
	
}