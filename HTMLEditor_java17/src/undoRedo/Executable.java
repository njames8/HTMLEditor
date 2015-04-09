package undoRedo;

import cmd.Command;
/**
 * 
 * @author John Mullen
 *
 */
public abstract class Executable {

	protected Command cmd;
	
	public abstract void undo();
	
	public abstract boolean isUndoable();

}
