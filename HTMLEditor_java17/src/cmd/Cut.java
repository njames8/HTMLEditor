/**
 * 
 */
package cmd;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.text.DefaultEditorKit.CutAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

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
	
	private String text;
	
	private Tab tab;
	
	int caretPos;
	
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
		try {
			text = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedFlavorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		tab = ew.getCurrentTab();
		caretPos = tab.getCaretPosition();

		
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
