/**
 * 
 */
package cmd;

import ui.EditorWindow;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import files.HTMLFile;

/**
 * Represents the Open command to open a file
 * 
 * @author Nick James
 * @author Matt Gallagher
 *
 */
public class OpenFile implements Command {

	/**
	 * The UI for selecting a file to open
	 */
	private JFileChooser chooser;

	/**
	 * The application window
	 */
	private EditorWindow eWindow;

	/**
	 * The collection of tabs in the window
	 */
	private JTabbedPane tabbedPane;

	/**
	 * OpenFile Constructor
	 * 
	 * @param w
	 *            - The application window
	 * @param j
	 *            - The pane of tabs in the application window
	 */
	public OpenFile(EditorWindow w, JTabbedPane j) {
		this.chooser = new JFileChooser();
		this.chooser.setFileFilter(new FileNameExtensionFilter("HTM/HTML",
				"html", "htm"));
		this.eWindow = w;
		this.tabbedPane = j;
	}

	/**
	 * Executes the openFile command: Opens a File Browser to load a file If
	 * file cannot be loaded, error message displays The load is cancelled A tab
	 * with the file's html code is added to the window
	 */
	@Override
	public void execute() {
		// If the user selects a file and clicks 'open'
		if (chooser.showOpenDialog(eWindow) == JFileChooser.APPROVE_OPTION) {
			HTMLFile f = new HTMLFile();

			// Load in the file
			if (!f.Load(chooser.getSelectedFile().getPath())) {

				JOptionPane.showMessageDialog(null, "Could not load file: "
						+ chooser.getSelectedFile().getName(),
						"Could not load file", JOptionPane.OK_OPTION);
				return;

			}
			eWindow.NewTab(f, tabbedPane);
			tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

		}
	}

}
