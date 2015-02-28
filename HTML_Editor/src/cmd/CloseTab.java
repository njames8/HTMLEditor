package cmd;

import ui.Tab;

public class CloseTab implements Command{
	private Tab t;
	public CloseTab(Tab t){
		this.t = t;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		t.close();
	}
	
}
