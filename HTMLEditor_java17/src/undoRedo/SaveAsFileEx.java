package undoRedo;
import cmd.*;

/**
 * 
 * @author John Mullen
 *
 */
public class SaveAsFileEx extends Executable{
	
	protected SaveAsFile cmd;
	
	public SaveAsFileEx(SaveAsFile c){
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
