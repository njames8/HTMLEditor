/**
 * 
 */
package cmd;

import ui.*;

/**
 * Represents a Save command
 * 
 * @author Nick James
 *
 */
public class SaveFile implements Command {
	/**
	 * The tab that holds the html file
	 */
	private Tab tab;

	/**
	 * The SaveFile Constructor
	 * 
	 * @param t
	 *            - The current tab
	 * @param w
	 *            - The application window
	 */
	public SaveFile(Tab t) {
		this.tab = t;
	}

	/**
	 * Executes the save command
	 */
	@Override
	public void execute() {
		if(!tab.getFile().isOnDisk())
		{
			SaveAsFile f = new SaveAsFile(tab);
			f.execute();
		}
		else
		{
			tab.getFile().save(tab.getText());
		}
	}
}