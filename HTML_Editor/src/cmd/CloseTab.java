package cmd;

import java.awt.Component;

import javax.swing.JTabbedPane;

import ui.Tab;

public class CloseTab implements Command{
	private Tab t;
	JTabbedPane j;
	public CloseTab(Component t, JTabbedPane j){
		this.t = (Tab) t;
		this.j = j;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		t.close();
		j.remove(j.getSelectedIndex());
	}
	
}
