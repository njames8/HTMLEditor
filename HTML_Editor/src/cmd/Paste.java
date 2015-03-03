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
	
	private JTabbedPane tabbedPane;
	
	public Paste(JTabbedPane j){
		super();
		this.tabbedPane = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		Tab t  = (Tab)tabbedPane.getSelectedComponent();
		t.getFile().Changed();
		
	}
}
