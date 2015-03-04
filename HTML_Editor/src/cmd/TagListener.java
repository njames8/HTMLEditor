/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
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
	private JTabbedPane tabbedPane;

	

	/**
	 * Constructs a TagListener Parses tag into a valid html tag
	 *
	 * @param tag
	 *            - the tag type to be inserted
	 * @param t
	 *            - the current tab
	 */
	public TagListener(String type, JTabbedPane t) {
		this.tag = type;
		this.tabbedPane = t;
		
	}

	/**
	 * Inserts the tag into the document Puts cursor in middle of inserted tags
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JScrollPane temp = (JScrollPane)tabbedPane.getSelectedComponent();
		JViewport temp2 = temp.getViewport();
		Tab t = (Tab)temp2.getView();
		
		try {
			t.getDocument().insertString(t.getCaretPosition(), tag, null);
			t.getFile().Changed();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
