package cmd;

import ui.EditorWindow;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import files.HTMLFile;

public class OpenFile implements Command {
	private JFileChooser chooser;
	private EditorWindow eWindow;
	private JTabbedPane tabbedPane;
	
	public OpenFile(JFileChooser c, EditorWindow w, JTabbedPane j){
		this.chooser = c;
		this.chooser.setFileFilter(new FileNameExtensionFilter("HTM/HTML", "html","htm"));
		this.eWindow = w;
		this.tabbedPane = j;
	}
	
	@Override
	public void execute() {
		// If the user selects a file and clicks 'open'
		if(chooser.showOpenDialog(eWindow) == JFileChooser.APPROVE_OPTION) {
			HTMLFile f = new HTMLFile();
			
			// Load in the file
			if(!f.Load(chooser.getSelectedFile().getPath())) {
				// TODO: message box saying we couldn't load the file
			}
			
			eWindow.NewTab(f, tabbedPane);
			tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
		}
	}
	
}
