package commands;

import ui.EditorWindow;

import javax.swing.JFileChooser;

import files.HTMLFile;

public class OpenFile implements Command {
	private JFileChooser chooser;
	private EditorWindow eWindow;
	
	public OpenFile(JFileChooser c, EditorWindow w){
		this.chooser = c;
		this.eWindow = w;
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
			
			eWindow.NewTab(f);
		}
	}
	
}
