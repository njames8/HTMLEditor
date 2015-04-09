package undoRedo;
import cmd.*;
/**
 * @author John Mullen
 */
public class SaveAsEx extends Executable {
	protected SaveFile cmd;
	
	public SaveAsEx(SaveFile c){
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
