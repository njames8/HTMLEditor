/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.BadLocationException;

import ui.EditorWindow;
import ui.Tab;

/**
 * Action listener for the insertion of tags Handles all basic HTML tags:
 * (<html>, <b>, <body>, etc).
 * Excluding: lists tables scripts
 * 
 * @author Nick James
 *
 */
public class InsertTag implements ActionListener {
	/**
	 * The type of HTML tag (html, bold, body, italic, etc)
	 */
	private String tag;
	
	// A self closing tag is an html tag which does not need a separate closing tag.
	// e.g. <img />
	private boolean selfClosing;
	
	/**
	 * Constructs a TagListener Parses tag into a valid html tag
	 *
	 * @param tag
	 *            - the tag type to be inserted
	 * @param t
	 *            - the current tab
	 */
	public InsertTag(String type) {
		this.tag = type;
		selfClosing = false;
	}
	
	public InsertTag(String type, boolean selfClosing) {
		this.tag = type;
		this.selfClosing = true;
	}
	
	// Takes the tag and makes it into a full html tag
	// head --> <head></head>
	private String getFullTag() {
		if(selfClosing)
			return "<" + tag + " />";
		
		return "<" + tag + "></" + tag + ">";
	}

	/**
	 * Inserts the tag into the document Puts cursor in middle of inserted tags
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Tab t = EditorWindow.getInstance().getCurrentTab();
		
		try {
			t.getDocument().insertString(t.getCaretPosition(), getFullTag(), null);
			t.getFile().changed();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
