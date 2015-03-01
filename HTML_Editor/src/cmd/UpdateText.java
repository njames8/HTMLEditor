/**
 * 
 */
package cmd;

import javax.swing.JTabbedPane;

import ui.Tab;
import files.HTMLFile;

/**
 * @author nick james
 *
 */
public class UpdateText implements Command {
	private String text;
	private HTMLFile file;
	private JTabbedPane tabbedPane;
	public UpdateText(Tab t, JTabbedPane j){
		this.text = t.getText();
		this.file = t.getFile();
		this.tabbedPane = j;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		file.UpdateText(text);
		tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), file.GetTabName());
	}
	
}
