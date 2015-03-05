package cmd;
import ui.Tab;
import Formatting.*;
/*
 * IndentCMD - Command obj for auto-indent calls
 * 
 * @author Adam Walsh
 */
public class IndentCMD implements Command{
	private Tab tab;
	public IndentCMD(Tab tab) {
		this.tab = tab;
	}
	
	@Override
	public void execute() {
		Indenter.autoIndent(tab.getText());
	}
}
