package cmd;

import ui.EditorWindow;

public class CloseAllTab implements Command {
	public void execute() {
		EditorWindow.getInstance().closeAll();
	}
}
