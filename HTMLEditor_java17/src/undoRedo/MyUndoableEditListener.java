/**
 * 
 */
package undoRedo;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * @author nick
 *
 */
public class MyUndoableEditListener implements UndoableEditListener{
	private UndoManager manager;
	public MyUndoableEditListener(UndoManager m){
		manager = m;
	}
	
	@Override
	public void undoableEditHappened(UndoableEditEvent e) {
		// TODO Auto-generated method stub
		manager.addEdit(e.getEdit());
		System.out.println(e.toString());
	}

}
