/**
 * 
 */
package cmd;

import ui.EditorWindow;

/**
 * @author Nick James
 *
 */
public class Close implements Command {

	
	public Close(){
		
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
