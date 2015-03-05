package cmd;
import javax.swing.JOptionPane;

import ui.Tab;
import parsing.*;
import ui.MessageBox;
/*
 * @author Adam Walsh
 */
public class ParseCMD implements Command {
	private Tab tab;
	public ParseCMD(Tab tab) {
		this.tab = tab;
	}
	
	@Override
	public void execute() {
		try {
			Parser.Parse(tab.getText());
		}
		catch (SyntaxException e) {
			new MessageBox("Syntax Exception", e.toString(), JOptionPane.ERROR_MESSAGE);
		}
	}

}