/**
 * 
 */
package cmd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.BadLocationException;

import ui.EditorWindow;
import ui.MessageBox;
import ui.Tab;

/**
 * Action listener for the insertion of tags Handles all basic HTML tags:
 * (<html>, <b>, <body>, etc).
 * 
 * 
 * @author Nick James
 *
 */
public class InsertTag implements ActionListener {
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
		if (selfClosing)
			return "<" + tag + " />\n";

		return "<" + tag + ">\n</" + tag + ">";
	}

	/**
	 * Inserts the tag into the document Puts cursor in middle of inserted tags
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Tab t = EditorWindow.getInstance().getCurrentTab();

		try {
			if (tag.contains("table")) {
				insertTable(t);
			} else {
				t.getDocument().insertString(t.getCaretPosition(),
						getFullTag(), null);
				t.getFile().changed();
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
				t.getDocument().insertString(t.getCaretPosition(),
						getFullTag(), null);
				t.setCaretPosition((getFullTag().length() / 2));
				for (; row > 0; row--) {
					t.getDocument().insertString(t.getCaretPosition(),
							"<tr>\n", null);
					for (int i = 0; i < col; i++) {
						t.getDocument().insertString(t.getCaretPosition(),
								"\t<td>\n\t</td>\n", null);
					}
					t.getDocument().insertString(t.getCaretPosition(),
							"</tr>\n", null);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,
						"Input was not valid. Please enter an integer.",
						"Input Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (BadLocationException e){
				e.printStackTrace();
			}
		}
	}

}
