/**
 * 
 */
package ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * @author nick
 *
 */
public class RightClickMenu extends JPopupMenu{
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;
	
	public RightClickMenu(){
		this.cut = new JMenuItem("Cut");
		this.copy = new JMenuItem("Copy");
		this.paste = new JMenuItem("Paste");
		
		add(cut);
		add(copy);
		add(paste);
	}
}
