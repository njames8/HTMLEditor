/**
 * 
 */
package cmd;
import java.io.IOException;

import ui.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import files.HTMLFile;


/**
 * Represents a SaveAs command
 * @author nick
 *
 */
public class SaveAsFile implements Command {
	/**
	 * The File Browser
	 */
	private JFileChooser chooser;
	
	
	/**
	 * The html file to be saved
	 */
	private HTMLFile file;
	
	/**
	 * the tab containing the html file
	 */
	private Tab tab;
	
	/**
	 * SaveAsFile Constructor
	 * @param w - The application window
	 * @param t - The current tab
	 */
	public SaveAsFile( Tab t){
		this.chooser = new JFileChooser();
		this.chooser.setFileFilter(new FileNameExtensionFilter("HTM/HTML", "html"));

		this.file = t.getFile();
		this.tab = t;
	}
	
	/**
	 * Executes the SaveAs command:
	 * 	A File Browser is opened
	 * 	Path to save is selected
	 * 		If path doesn't exist, an error message appears
	 * 		The save is cancelled
	 * 	The file name is set to what is typed in the File Browser
	 * 	The file is saved
	 */
	@Override
	public void execute() {
		// If the user selects a file and clicks 'open'
		if(chooser.showSaveDialog(tab) == JFileChooser.APPROVE_OPTION) {
			
			// Load in the file
			if(!file.Load(chooser.getSelectedFile().getPath())) {
				JOptionPane.showMessageDialog(null, "Could not save file: "
						+ chooser.getSelectedFile().getName(),
						"Could not save file",JOptionPane.OK_OPTION);
				return;
			}
			//file.setLocation(chooser.getCurrentDirectory().getPath());
		
			file.setName(chooser.getSelectedFile().getName());
			
			try {
				file.SaveAs(chooser.getSelectedFile().getCanonicalPath(), tab);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}