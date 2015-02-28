package cmd;

import java.awt.Component;

import ui.Tab;

public class CloseTab implements Command{
	private Tab t;
	public CloseTab(Component t){
		this.t = (Tab) t;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		t.close();
	}
	
}
