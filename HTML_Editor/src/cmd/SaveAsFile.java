package cmd;

import java.io.IOException;

import ui.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import files.HTMLFile;

public class SaveAsFile implements Command {
	private JFileChooser chooser;
	private EditorWindow eWindow;
	private HTMLFile file;
	private Tab t;
	
	public SaveAsFile(JFileChooser c, EditorWindow w, Tab t){
		this.chooser = c;
		this.chooser.setFileFilter(new FileNameExtensionFilter("HTM/HTML", "html"));
		this.eWindow = w;
		this.file = t.getFile();
		this.t = t;
	}
	
	@Override
	public void execute() {
		// If the user selects a file and clicks 'open'
		if(chooser.showSaveDialog(eWindow) == JFileChooser.APPROVE_OPTION) {
			
			// Load in the file
			if(!file.Load(chooser.getSelectedFile().getPath())) {
				// TODO: message box saying we couldn't load the file
			}
			//file.setLocation(chooser.getCurrentDirectory().getPath());
		
			file.setName(chooser.getSelectedFile().getName());
			
			try {
				file.SaveAs(chooser.getSelectedFile().getCanonicalPath(), eWindow, t);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}