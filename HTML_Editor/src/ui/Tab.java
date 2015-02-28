package ui;
import javax.swing.*;

import files.HTMLFile;

public class Tab extends JTextPane {
	private HTMLFile file;
	
	public Tab() {
		super();
		this.file = new HTMLFile();
	}
	
	public Tab(HTMLFile file) {
		super();
		this.file = file;
		this.setText(file.getText());
	}
	
	public void close(){
		// TODO close tab method
		System.out.println("CLOSE!");
		
	}
	public HTMLFile getFile(){
		return file;
	}
}
