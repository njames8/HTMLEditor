package undoRedo;

import cmd.Command;
/**
 * 
 * @author John Mullen
 *
 */
public abstract class Executable {

	public Command cmd;
	
	public abstract void undo();
	
	public abstract boolean isUndoable();

}
