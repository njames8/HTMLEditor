/**
 * 
 */
package cmd;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

import ui.*;
import formatting.*;

/**
 * Action listener for the insertion of tags Handles all basic HTML tags:
 * (<html>, <b>, <body>, etc).
 * 
 * 
 * @author Nick James
 * @author Matthew Gallagher
 * 
 */
public class CopyOfInsertTag implements ActionListener {
	/**
	 * The type of HTML tag (html, bold, body, italic, etc)
	 */
	private String tag;

	// A self closing tag is an html tag which does not need a separate closing
	// tag.
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
	public CopyOfInsertTag(String type) {
		this.tag = type;
		selfClosing = false;
	}

	public CopyOfInsertTag(String type, boolean selfClosing) {
		this.tag = type;
		this.selfClosing = true;
	}

	// Takes the tag and makes it into a full html tag
	// head --> <head></head>
	private String getFullTag() {
		if (selfClosing)
			return "<" + tag + " />\n";

		return "<" + tag + ">\n\n</" + tag + ">";
	}

	private BaseTag makeNewTag(Tab t, String text, String l) {
		int pos = t.getCaretLineNumber();
		return new BaseTag(text, pos, pos + 1, false, null, l);
	}

	private void insertToTab(Tab t, BaseTag base) throws BadLocationException {
		int pos = t.getCaretLineNumber();
		if (t.head != null) {
			int indent = t.head.getIndentLevel(pos);
			t.head.addChild(base, pos);
			t.getDocument().insertString(t.getCaretPosition(),
					base.getText(indent), null);
		} else {
			t.head = base;
			t.getDocument().insertString(t.getCaretPosition(),
					t.head.getText(0), null);
		}
	}

	/**
	 * Inserts the tag into the document Puts cursor in middle of inserted tags
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Tab t = EditorWindow.getInstance().getCurrentTab();
		// System.out.println(tag);
		try {
			if (tag.contains("table")) {
				insertTable(t);
			} else if (tag.equals("a")) {
				insertATag(t);
			} else {
				insertToTab(t, makeNewTag(t, tag, null));
			}

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void insertTable(Tab t) {
		JPanel panel = new JPanel();
		JSpinner rows = new JSpinner(new SpinnerNumberModel(0, 0,
				Integer.MAX_VALUE, 1));
		JSpinner cols = new JSpinner(new SpinnerNumberModel(0, 0,
				Integer.MAX_VALUE, 1));
		panel.add(new JLabel("Rows: "));
		panel.add(rows);
		panel.add(new JLabel("Columns: "));
		panel.add(cols);
		JOptionPane pane = new JOptionPane(panel);
		pane.setSize(300, 150);
		int tableSize = pane.showOptionDialog(null, panel, "Table Size",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, null);
		if (tableSize == JOptionPane.OK_OPTION) {
			try {
				int row = (Integer) rows.getValue();
				int col = (Integer) cols.getValue();
				BaseTag base = makeNewTag(t, tag, null);
				// t.getDocument().insertString(t.getCaretPosition(),
				// getFullTag(), null);
				// t.setCaretPosition((getFullTag().length() / 2));
				for (; row > 0; row--) {
					// t.getDocument().insertString(t.getCaretPosition(),
					// "<tr>\n", null);
					BaseTag c1 = makeNewTag(t, "tr", null);
					for (int i = 0; i < col; i++) {
						c1.addChild(makeNewTag(t, "td", null));
						// t.getDocument().insertString(t.getCaretPosition(),
						// "\t<td>\n\t</td>\n", null);
					}
					// t.getDocument().insertString(t.getCaretPosition(),
					// "</tr>\n", null);
					base.addChild(c1);
				}
				insertToTab(t, base);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,
						"Input was not valid. Please enter an integer.",
						"Input Error", JOptionPane.ERROR_MESSAGE);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	private void insertATag(Tab t) {
		
		String url = JOptionPane.showInputDialog(null, "Link");

		BaseTag base;
		if (url == null) {
			return;
		}
		base = makeNewTag(t, "a", url);
		try {
			insertToTab(t, base);

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
