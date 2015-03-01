package ui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import cmd.UpdateText;
import files.HTMLFile;

public class Tab extends JTextPane {
	private HTMLFile file;
	private boolean focus; 
	private JTabbedPane j;
	public Tab() {
		super();
		this.file = new HTMLFile();
	}
	
	public Tab(HTMLFile file, JTabbedPane j) {
		super();
		this.file = file;
		this.j = j;
		this.setText(file.getText());
		this.addKeyListener(new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				UpdateText t = new UpdateText((Tab)e.getSource(), (JTabbedPane) e.getComponent().getParent());
				t.execute();
			}
			
		});
	}
	
	public void close(){
		// TODO close tab method
		//Prompt to save if changes have been made and not new
		
	}
	public HTMLFile getFile(){
		return file;
	}
	public boolean getFocus(){
		return focus;
	}
	public void setFocus(boolean b){
		focus = b;
	}
}
