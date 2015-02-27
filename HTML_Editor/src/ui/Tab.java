package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	}
}
