/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;

import javax.swing.text.DefaultEditorKit.PasteAction;

import ui.EditorWindow;
import ui.Tab;

/**
 * Provides pasting function
 * @author Nick James
 *
 */
@SuppressWarnings("serial")
public class Paste extends PasteAction {
	
	/**
	 * Constructor
	 */
	public Paste(){
		super();
	}
	
	/**
	 * Paste text when clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		Tab t = EditorWindow.getInstance().getCurrentTab();
		
		t.getFile().changed();
	}
}
