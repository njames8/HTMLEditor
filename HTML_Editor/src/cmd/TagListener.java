/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.text.BadLocationException;

import ui.Tab;

/**
 * Action listener for the insertion of tags Handles all basic HTML tags:
 * (<html>, <b>, <body>, etc).
 * Excluding: lists tables scripts
 * 
 * @author Nick James
 *
 */
public class TagListener implements ActionListener {
	/**
	 * The type of HTML tag (html, bold, body, italic, etc)
	 */
	private String tag;

	/**
	 * the current tab
	 */
	private Tab tab;

	private UpdateText update;

	/**
	 * Constructs a TagListener Parses tag into a valid html tag
	 *
	 * @param tag
	 *            - the tag type to be inserted
	 * @param t
	 *            - the current tab
	 */
	public TagListener(String type, Tab t) {
		this.tag = type;
		this.tab = t;
		this.update = new UpdateText(tab, (JTabbedPane) (tab.getParent()));
		
	}

	/**
	 * Inserts the tag into the document Puts cursor in middle of inserted tags
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			tab.getDocument().insertString(tab.getCaretPosition(), this.tag,
					null);
			tab.setCaretPosition(tab.getCaretPosition()
					- (this.tag.length() / 2) - 1);
			update.execute();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
