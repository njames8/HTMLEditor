/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;

import javax.swing.text.DefaultEditorKit.PasteAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

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
	
	private UndoManager manager;
	
	private String text;
	
	/**
	 * Constructor
	 */
	public Paste(EditorWindow e){
		super();
		ew = e;
		//manager = m;
		//manager.addEdit(this);
	}
	
	/**
	 * Paste text when clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		execute();
		//this.text = e.
	}
	
	/**
	 * pastes text into the tab
	 */
	@Override
	public void execute(){
		ew.getCurrentTab().getFile().changed();
	}

}
