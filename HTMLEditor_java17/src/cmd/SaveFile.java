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
	private Tab t;

	/**
	 * The SaveFile Constructor
	 * 
	 * @param t
	 *            - The current tab
	 * @param w
	 *            - The application window
	 */
	public SaveFile(Tab t) {
		this.t = t;
	}

	/**
	 * Executes the save command
	 */
	@Override
	public void execute() {
		if(!t.getFile().isOnDisk())
		{
			SaveAsFile f = new SaveAsFile(t);
			f.execute();
		}
		else
		{
			t.getFile().save(t.getText());
		}
	}
}