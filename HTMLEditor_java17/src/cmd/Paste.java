/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;

import javax.swing.text.DefaultEditorKit.PasteAction;

import ui.EditorWindow;
import ui.Tab;

/**
 * @author nick
 *
 */
@SuppressWarnings("serial")
public class Paste extends PasteAction {
	
	public Paste(){
		super();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		Tab t = EditorWindow.getInstance().getCurrentTab();
		
		t.getFile().changed();
	}
}
