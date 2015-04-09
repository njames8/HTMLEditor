package cmd;

import ui.EditorWindow;

public class CloseAllTab implements Command {
	EditorWindow ew;
	public CloseAllTab(EditorWindow e){
		ew = e;
	}
	public void execute() {
		ew.closeAll();
	}
}
