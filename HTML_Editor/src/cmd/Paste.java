/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.text.DefaultEditorKit.PasteAction;

import ui.Tab;

/**
 * @author nick
 *
 */
public class Paste extends PasteAction {
	
	UpdateText update;
	
	public Paste(Tab t, JTabbedPane j){
		super();
		update = new UpdateText(t,j);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		update.execute();
		
	}
}
