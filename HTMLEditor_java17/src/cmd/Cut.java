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
public class Cut extends CutAction{
	EditorWindow ew;
	public Cut(EditorWindow e){
		super();
		ew = e;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		
		Tab t = ew.getCurrentTab();
		t.getFile().changed();
	}
}
