package ui;
import javax.swing.*;

import files.HTMLFile;

public class Tab extends JTextPane {
	public HTMLFile file;
	
	public Tab() {
		super();
		this.file = new HTMLFile();
	}
	
	public Tab(HTMLFile file) {
		super();
		this.file = file;
		this.setText(file.getText());
	}
	
}
