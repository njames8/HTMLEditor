package cmd;
import ui.Tab;
import parsing.*;
import ui.MessageBox;

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
			MessageBox msg = new MessageBox("Syntax Exception",
					e.toString(), 1);
		}
	}

}
