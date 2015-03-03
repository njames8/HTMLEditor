/**
 * 
 */
package cmd;

import javax.swing.JTabbedPane;

import ui.Tab;
import files.HTMLFile;

/**
 * @author nick james Represents a call to update the html file's text
 */
public class UpdateText implements Command {
	/**
	 * The text to update the file with
	 */
	private String text;

	/**
	 * the html file to update
	 */
	private HTMLFile file;

	/*
	 * the collection of tabs
	 */
	private JTabbedPane tabbedPane;

	/**
	 * Constructs UpdateTab
	 * 
	 * @param t
	 *            - the current tab
	 * @param j
	 *            - the collection of tabs
	 */
	public UpdateText(Tab t, JTabbedPane j) {
		this.text = t.getText();
		this.file = t.getFile();
		this.tabbedPane = j;
	}

	/**
	 * Updates the text of the HTML file and updates the title of the current
	 * tab to reflect the need to be saved
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		/*int curTab = tabbedPane.getSelectedIndex();
		
		tabbedPane.getTabComponentAt(curTab);
		tabbedPane.setTitleAt(curTab, file.GetTabName());*/
	}

}
