package commands;

import ui.EditorWindow;

import javax.swing.JFileChooser;

public class OpenFile implements Command {
	private JFileChooser chooser;
	private EditorWindow eWindow;
	
	public OpenFile(JFileChooser c, EditorWindow w){
		this.chooser = c;
		this.eWindow = w;
	}
	
	@Override
	public void execute() {
		// TODO auto-generated method stub
		chooser.showOpenDialog(eWindow);
	}
	
}
