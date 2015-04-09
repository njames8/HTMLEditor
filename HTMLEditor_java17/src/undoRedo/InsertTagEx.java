package undoRedo;
import cmd.*;

/**
 * @author John Mullen
 */
public class InsertTagEx extends Executable{
	
	private InsertTag cmd;
	
	public InsertTagEx(InsertTag c){
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
