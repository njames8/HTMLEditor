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
	public Cut(){
		super();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		
		Tab t = EditorWindow.getInstance().getCurrentTab();
		t.getFile().changed();
	}
}
