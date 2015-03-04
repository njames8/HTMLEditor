/**
 * 
 */
package ui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.text.DefaultEditorKit;

import cmd.Cut;
import cmd.Paste;

/**
 * @author nick
 *
 *
 */
public class RightClickMenu extends JPopupMenu{
	
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;
	
	public RightClickMenu(JTabbedPane t){
		
		this.cut = new JMenuItem(new Cut(t));
		this.copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		this.paste = new JMenuItem(new Paste(t));
		
		
		add(cut);
		add(copy);
		add(paste);
	}
}
