/**
 * Represents the call to close the editor window
 */
package cmd;

import ui.EditorWindow;

/**
 * @author Nick James
 *
 */
public class Close implements Command {
	
	public Close() { }
	
	/**
	* Closes the application
	*/
	@Override
	public void execute() {
		EditorWindow w = EditorWindow.getInstance();
		
		if (w.closeAll()) {

			System.exit(0);
		}
		
	}

}
