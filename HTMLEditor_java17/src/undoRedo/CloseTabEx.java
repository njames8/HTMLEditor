package undoRedo;

import cmd.CloseTab;

/**
 * @author John Mullen
 */
public class CloseTabEx extends Executable  {
	
	private CloseTab cmd;
	
	public CloseTabEx(CloseTab c){
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
