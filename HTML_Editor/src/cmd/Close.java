/**
 * Represents the call to close the editor window
 */
package cmd;

import javax.swing.JTabbedPane;

import ui.Tab;

/**
 * @author Nick James
 *
 */
public class Close implements Command {
	private JTabbedPane pane;
	
	public Close(JTabbedPane p) {
		this.pane = p;
	}
	/**
	* Closes the application
	*/
	@Override
	public void execute() {
		for (int x = 0; x < pane.getTabCount(); x++ ){
			if(((Tab) pane.getComponentAt(x)).close() == false){
				return;
			}
		}
		System.exit(0);
		
	}

}
