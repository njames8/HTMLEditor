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
	
	EditorWindow ew;
	/**
	 * Constructor
	 */
	public Paste(EditorWindow e){
		super();
		ew = e;
	}
	
	/**
	 * Paste text when clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		ew.getCurrentTab().getFile().changed();
	}
}
