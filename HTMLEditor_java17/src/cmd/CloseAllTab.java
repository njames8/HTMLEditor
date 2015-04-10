package cmd;

import ui.EditorWindow;
/**
 * 
 * @author nick james
 * Closes all of the tabs in the EditorWindow
 */
public class CloseAllTab implements Command {
	/**
	 * the EditorWindow
	 */
	private EditorWindow ew;
	
	/**
	 * constructor
	 * @param e - the main editorWindow
	 */
	public CloseAllTab(EditorWindow e){
		ew = e;
	}
	/**
	 * closes all tabs
	 */
	public void execute() {
		ew.closeAll();
	}
}
