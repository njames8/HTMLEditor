package cmd;

import ui.*;

import javax.swing.JFileChooser;

import files.HTMLFile;

public class SaveAsFile implements Command {
	private JFileChooser chooser;
	private EditorWindow eWindow;
	private HTMLFile file;
	
	public SaveAsFile(JFileChooser c, EditorWindow w, Tab t){
		this.chooser = c;
		this.eWindow = w;
		this.file = t.getFile();
	}
	
	@Override
	public void execute() {
		// If the user selects a file and clicks 'open'
		if(chooser.showSaveDialog(eWindow) == JFileChooser.APPROVE_OPTION) {
			
			// Load in the file
			if(!file.Load(chooser.getSelectedFile().getPath())) {
				// TODO: message box saying we couldn't load the file
			}
			file.setLocation(chooser.getCurrentDirectory().getPath());
			file.Save();
		}
	}
}