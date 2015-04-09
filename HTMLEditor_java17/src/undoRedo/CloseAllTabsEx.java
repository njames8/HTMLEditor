package undoRedo;

import cmd.CloseAllTab;

/**
 * @author John Mullen
 */
public class CloseAllTabsEx extends Executable  {
	
	private CloseAllTab cmd;
	
	public CloseAllTabsEx(CloseAllTab c){
		this.cmd = c;
	}

	@Override
	public void undo() {
		// N/A
		
	}

	@Override
	public boolean isUndoable() {
		return false;
		
	}

}
