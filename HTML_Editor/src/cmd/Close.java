/**
 * Represents the call to close the editor window
 */
package cmd;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;

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
			
			JScrollPane temp = (JScrollPane)pane.getComponentAt(x);
			JViewport temp2 = temp.getViewport();
			Tab t = (Tab)temp2.getView();
			
			if(t.close() == false){
				return;
			}
		}
		System.exit(0);
		
	}

}
