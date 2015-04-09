package undoRedo;

import cmd.*;

/**
 * @author John Mullen
 */
public class CutEx extends Executable {
	
	private Cut cmd;

	public CutEx(Cut c){
		this.cmd = c;
	}

	@Override
	public void undo() {
		// REQUIRES MOMENTO
		
	}

	@Override
	public boolean isUndoable() {
		return true;
	}
}
