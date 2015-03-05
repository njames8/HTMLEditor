/**
 * 
 */
package ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;

import cmd.Cut;
import cmd.Paste;

/**
 * @author nick
 *
 *
 */
@SuppressWarnings("serial")
public class RightClickMenu extends JPopupMenu{
	
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;
	
	public RightClickMenu() {
		this.cut = new JMenuItem(new Cut());
		this.copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		this.paste = new JMenuItem(new Paste());
		
		add(cut);
		add(copy);
		add(paste);
	}
}
