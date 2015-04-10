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
public class Paste extends PasteAction implements Command{
	
	/**
	 * The main EditorWindow
	 */
	private EditorWindow ew;
	
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
		execute();
	}
	
	/**
	 * pastes text into the tab
	 */
	@Override
	public void execute(){
		ew.getCurrentTab().getFile().changed();
	}
}
