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
 * @author Nick James
 *
 *
 */
@SuppressWarnings("serial")
public class RightClickMenu extends JPopupMenu{
	
	/**
	 * Cut menu item
	 */
	private JMenuItem cut;
	
	/**
	 * copy menu item
	 */
	private JMenuItem copy;
	
	/**
	 * paste menu item
	 */
	private JMenuItem paste;
	
	
	/**
	 * Constructor
	 */
	public RightClickMenu(EditorWindow e) {
		this.cut = new JMenuItem(new Cut(e));
		this.copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		this.paste = new JMenuItem(new Paste(e));
		
		add(cut);
		add(copy);
		add(paste);
	}
}
