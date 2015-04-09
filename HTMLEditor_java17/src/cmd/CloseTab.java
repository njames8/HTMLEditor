/**
 * Represents a call to close a tab in the UI
 */
package cmd;

import javax.swing.JTabbedPane;

import ui.EditorWindow;
import ui.Tab;

/**
 * @author Nick James
 */
@SuppressWarnings("unused")
public class CloseTab implements Command {
	private EditorWindow ew;

	/**
	 * CloseTab constructor
	 * 
	 */
	public CloseTab(EditorWindow e) {
		ew = e;
	}

	/**
	 * Closes the tab and removes it from its JTabbedPane container
	 */
	@Override
	public void execute() {
		ew.close();
	}

}
