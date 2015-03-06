/**
 * 
 */
package cmd;

import java.io.IOException;

import ui.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Represents a SaveAs command
 * 
 * @author Nick James
 *
 */
public class SaveAsFile implements Command {
	/**
	 * The File Browser
	 */
	private JFileChooser chooser;

	/**
	 * the tab containing the html file
	 */
	private Tab tab;

	/**
	 * SaveAsFile Constructor
	 * 
	 * @param w
	 *            - The application window
	 * @param t
	 *            - The current tab
	 */
	public SaveAsFile(Tab t) {
		this.chooser = new JFileChooser();
		this.chooser.setFileFilter(new FileNameExtensionFilter("HTM/HTML",
				"html"));

		this.tab = t;
	}

	/**
	 * Executes the SaveAs command: A File Browser is opened Path to save is
	 * selected If path doesn't exist, an error message appears The save is
	 * cancelled The file name is set to what is typed in the File Browser The
	 * file is saved
	 */
	@Override
	public void execute() {
		// If the user selects a file and clicks 'open'
		if (chooser.showSaveDialog(tab) == JFileChooser.APPROVE_OPTION) {
			try {
				String path = chooser.getSelectedFile().getCanonicalPath();
				
				if(!path.endsWith(".html")) {
					path += ".html";
				}
				
				tab.saveAs(path, tab.getText());
				//System.out.println(tab.GetTitle());
			} catch (IOException e) {
				// TODO
				e.printStackTrace();
			}
		}
	}
}