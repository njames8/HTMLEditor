/**
 * 
 */
package cmd;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.ImageFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	
	/**
	 * Constructor
	 * @param type - html tag
	 * @param selfClosing - is this tag self closing?
	 */
	public CopyOfInsertTag(String type, boolean selfClosing) {
		this.tag = type;
		this.selfClosing = true;
	}

	/**
	 *  Takes the tag and makes it into a full html tag
	 *  head --> <head></head>
	 */
	private String getFullTag() {
		if (selfClosing)
			return "<" + tag + " />\n";

		return "<" + tag + ">\n\n</" + tag + ">";
	}
	
	/**
	 * makes a new html tag
	 * @param t - the current tab
	 * @param text - the text of the html tag
	 * @param l - the tag's link (can be null)
	 * @param sc - is this tag self closing?
	 * @return - a constructed tag
	 */
	private BaseTag makeNewTag(Tab t, String text, String l, boolean sc) {
		int pos = t.getCaretLineNumber();
		if (sc){
			return new SelfClosingTag(text, pos, pos + 1, l);
		}
		return new BaseTag(text, pos, pos + 1, false, null, l);
	}
	
	/**
	 * inserts a constructed html tag into a tab
	 * @param t - the current tab
	 * @param base - the tag to insert
	 * @throws BadLocationException - if the spot on the tab doesnt exist
	 */
	private void insertToTab(Tab t, BaseTag base) throws BadLocationException {
		int pos = t.getCaretLineNumber();
		if (t.head != null) {
			int indent = t.head.getIndentLevel(pos);
			t.head.addChild(base, pos);
			if (!this.selfClosing){
				//TODO get selfCLosing tag text
			}
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
			} else if (tag.equals("img")){
				insertImg(t);
			} else {
				insertToTab(t, makeNewTag(t, tag, null, this.selfClosing));
			}

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * inserts a table into the tab
	 * @param t - the current tab
	 */
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
				BaseTag base = makeNewTag(t, tag, null, this.selfClosing);
				// t.getDocument().insertString(t.getCaretPosition(),
				// getFullTag(), null);
				// t.setCaretPosition((getFullTag().length() / 2));
				for (; row > 0; row--) {
					// t.getDocument().insertString(t.getCaretPosition(),
					// "<tr>\n", null);
					BaseTag c1 = makeNewTag(t, "tr", null, this.selfClosing);
					for (int i = 0; i < col; i++) {
						c1.addChild(makeNewTag(t, "td", null, this.selfClosing));
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

	/**
	 * Inserts an a tag into the text area and tag tree
	 * @param t - the current tab
	 */
	private void insertATag(Tab t) {
		String url = JOptionPane.showInputDialog(null, "Link");
		BaseTag base;
		if (url == null) {
			return;
		}
		base = makeNewTag(t, "a", url, this.selfClosing);
		try {
			insertToTab(t, base);

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * insert img tag into tab
	 * @param t - current tab
	 */
	private void insertImg(Tab t){
		
		String url = getImagePath();
		BaseTag base;
		if (url == null) {
			return;
		}
		base = makeNewTag(t, "img", url, this.selfClosing);
		try {
			insertToTab(t, base);

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * gets the path of an image 
	 * @return - the path of an image
	 */
	private String getImagePath(){
		JPanel p = new JPanel(new BorderLayout());
		final JTextArea t = new JTextArea(1,30);
		
		final JFileChooser c = new JFileChooser();
		FileFilter imageFilter = new FileNameExtensionFilter(
			    "Image files", ImageIO.getReaderFileSuffixes());
		c.addChoosableFileFilter(imageFilter);
		
		JButton b = new JButton("Browse");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				c.showOpenDialog(null);
				if (c.getSelectedFile() != null) {
					String path = c.getSelectedFile().getAbsolutePath();
					t.setText(path);
				}
			}
		});
		
		p.add(t, BorderLayout.CENTER);
		p.add(b,BorderLayout.EAST);
			
		int choice = JOptionPane.showConfirmDialog(null, p, "Choose Source", 0);
		
		if (choice == JOptionPane.YES_OPTION){
			return t.getText();
		}
		return null;
		
	}
}
