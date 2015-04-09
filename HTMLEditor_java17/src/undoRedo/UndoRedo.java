package undoRedo;

import java.util.Stack;

import cmd.Command;

/**
 * 
 * @author John Mullen
 *
 */
public class UndoRedo {
	private Stack<Executable> recentCommands = new Stack<Executable>();
	private Stack<Executable> recentUndos = new Stack<Executable>();
	
	public UndoRedo(){};
	
	/**
	 * Adds the executable to the recent commands stack
	 */
	public void addCommand(Executable cmd){
		recentCommands.push(cmd);
		while(recentUndos.empty() == false){
			recentUndos.pop();
		}
	}
	
	public void undo(){
		Executable cmd = recentCommands.pop();
		cmd.undo();
	}
	
	
	

}
