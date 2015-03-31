package cmd;
import javax.swing.JOptionPane;

import ui.Tab;
import parsing.*;
import ui.MessageBox;
/**
 * @author Adam Walsh
 */
public class ParseCMD implements Command {
	/**
	 * The current Tab
	 */
	private Tab tab;
	
	/**
	 * Constructor
	 * @param tab - the current tab
	 */
	public ParseCMD(Tab tab) {
		this.tab = tab;
	}
	
	
	/**
	 * Parse and validates the HTML code
	 */
	@Override
	public void execute() {
//*
		try {
			Parser.Parse(tab.getText());
		}
		catch (SyntaxException e) {
			new MessageBox("Syntax Exception", e.toString(), JOptionPane.ERROR_MESSAGE);
		}
//*/	System.out.println(tab.head);
	}

}
