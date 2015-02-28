package cmd;

import ui.*;

import javax.swing.JFileChooser;

import files.HTMLFile;

public class SaveFile implements Command {
	private HTMLFile file;
	
	public SaveFile(Tab t){
			file = t.getFile();
	}
	
	@Override
	public void execute() {
		file.Save();
	}
	
}