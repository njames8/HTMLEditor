package cmd;

import ui.Tab;
/**
 * IndentCMD - Command object for auto-indent calls
 * 
 * @author Adam Walsh
 */
public class IndentCMD implements Command{
	/**
	 * The current Tab
	 */
	private Tab tab;
	
	/**
	 * Constructor
	 * @param tab - the current tab
	 */
	public IndentCMD(Tab tab) {
		this.tab = tab;
	}
	
	/**
	 * Perform the indent
	 */
	@Override
	public void execute() {
		this.tab.indenter.openIndent(tab.getText());
	}
}
