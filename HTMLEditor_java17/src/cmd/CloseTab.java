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
public class CloseTab implements Command {
	/**
	 * The current Tab
	 */
	private Tab tab;

	/**
	 * The container that contains Tab t
	 */
	private JTabbedPane tabbedPane;

	/**
	 * CloseTab constructor
	 * 
	 * @param t
	 *            - The Current Tab
	 * @param j
	 *            - The container that holds Tab t
	 */
	public CloseTab(Tab t, JTabbedPane j) {
		this.tab = t;
		this.tabbedPane = j;

	}

	/**
	 * Closes the tab and removes it from its JTabbedPane container
	 */
	@Override
	public void execute() {
		EditorWindow.getInstance().close();
	}

}
