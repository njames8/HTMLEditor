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
 *
 */
@SuppressWarnings("serial")
public class Cut extends CutAction implements Command{
	EditorWindow ew;
	public Cut(EditorWindow e){
		super();
		ew = e;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		execute();
	}
	
	@Override
	public void execute(){
		Tab t = ew.getCurrentTab();
		t.getFile().changed();
	}
}
