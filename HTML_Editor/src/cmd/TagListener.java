/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.BadLocationException;

import ui.Tab;

/**
 * @author nick james
 *
 */
public class TagListener implements ActionListener {
	private String type;
	private Tab tab;
	
	public TagListener(String tag, Tab t){
		this.type = tag;
		this.type = this.type.toLowerCase();
		if(!(this.type.equals("html") || this.type.equals("body"))){
			this.type = this.type.substring(0, 1);
		}
		this.type = "<" + this.type + ">" + "</" + this.type +">";
		this.tab = t;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			tab.getDocument().insertString(tab.getCaretPosition(), this.type, null);
			tab.setCaretPosition(tab.getCaretPosition()-(this.type.length()/2)-1);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
