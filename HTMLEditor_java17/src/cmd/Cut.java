/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;

import javax.swing.text.DefaultEditorKit.CutAction;

import ui.EditorWindow;
import ui.Tab;

/**
 * @author Nick James
 * cuts selected text and copies it to the clipboard
 */
@SuppressWarnings("serial")
public class Cut extends CutAction implements Command{
	/**
	 * the main EditorWindow
	 */
	private EditorWindow ew;
	public Cut(EditorWindow e){
		super();
		ew = e;
	}
	
	/**
	 * when cut is clicked execute the action
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		execute();
	}
	
	/**
	 * cut the text onto the clipboard
	 */
	@Override
	public void execute(){
		Tab t = ew.getCurrentTab();
		t.getFile().changed();
	}
}
