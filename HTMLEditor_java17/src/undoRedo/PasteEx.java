package undoRedo;
import cmd.*;

/**
 * 
 * @author John Mullen
 *
 */
public class PasteEx extends Executable {
	
	protected Paste cmd;
	
	public PasteEx(Paste c){
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
