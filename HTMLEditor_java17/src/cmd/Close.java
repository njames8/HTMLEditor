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
	/**
	 * the editorWindow
	 */
	private EditorWindow ew;
	
	/**
	 * constructor
	 * @param e - the editorWindow
	 */
	public Close(EditorWindow e) { 
		ew = e;
	}
	
	/**
	* Closes the application
	*/
	@Override
	public void execute() {
		EditorWindow w = ew;
		
		if (w.closeAll()) {

			System.exit(0);
		}
		
	}

}
