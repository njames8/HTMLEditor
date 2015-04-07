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
	
	private boolean saved;

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
		saved = false;
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
			
			this.saved = f.getSaved();
		}
		else
		{
			this.saved = tab.saveFile();
		}
	}
	
	public boolean getSaved() {
		return saved;
	}
}