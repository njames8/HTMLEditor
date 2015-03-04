/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import javax.swing.text.DefaultEditorKit.CutAction;

import ui.Tab;

/**
 * @author nick
 *
 */
public class Cut extends CutAction{
	private JTabbedPane tabbedPane;
	
	public Cut(JTabbedPane j){
		super();
		this.tabbedPane = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		JScrollPane temp = (JScrollPane)tabbedPane.getSelectedComponent();
		JViewport temp2 = temp.getViewport();
		Tab t = (Tab)temp2.getView();
		t.getFile().Changed();
		
	}
}
