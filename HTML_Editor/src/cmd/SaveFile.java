package cmd;

import java.io.IOException;

import ui.*;

import javax.swing.JFileChooser;

import files.HTMLFile;

public class SaveFile implements Command {
	private HTMLFile file;
	private Tab t;
	private EditorWindow win;
	
	public SaveFile(Tab t, EditorWindow w){
			this.file = t.getFile();
			this.win = w;
			this.t = t;
	}
	
	@Override
	public void execute() {
		file.Save(win, t);
	}
	
}