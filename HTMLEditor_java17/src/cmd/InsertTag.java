/**
 * 
 */
package cmd;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

import ui.*;
import formatting.*;
import cmd.ImagePreviewCMD;

/**
 * Action listener for the insertion of tags Handles all basic HTML tags:
 * (<html>, <b>, <body>, etc).
 * 
 * 
 * @author Nick James
 * @author Matthew Gallagher
 * 
 */
@SuppressWarnings("unused")
public class InsertTag implements ActionListener, UndoableEdit {
	/**
	 * The type of HTML tag (html, bold, body, italic, etc)
	 */
	private String tag;
	
	private BaseTag thisTag;

	// A self closing tag is an html tag which does not need a separate closing
	// tag.
	// e.g. <img />
	private boolean selfClosing;
	private EditorWindow ew;
	/**
	 * Constructs a TagListener Parses tag into a valid html tag
	 *
	 * @param tag
	 *            - the tag type to be inserted
	 * @param t
	 *            - the current tab
	 */
	public InsertTag(String type, EditorWindow e) {
		this.tag = type;
		ew = e;
		selfClosing = false;
	}

	/**
	 * Constructor
	 * 
	 * @param type
	 *            - html tag
	 * @param selfClosing
	 *            - is this tag self closing?
	 */
	public InsertTag(String type, boolean selfClosing) {
		this.tag = type;
		this.selfClosing = true;
	}

	/**
	 * makes a new html tag
	 * 
	 * @param t
	 *            - the current tab
	 * @param text
	 *            - the text of the html tag
	 * @param l
	 *            - the tag's link (can be null)
	 * @param sc
	 *            - is this tag self closing?
	 * @return - a constructed tag
	 */
	private BaseTag makeNewTag(Tab t, String text, String l, boolean sc) {
		int pos = t.getCaretLineNumber();
		if (sc) {
			this.thisTag =  new SelfClosingTag(text, pos, pos, l);
			return thisTag;
		}
		this.thisTag =  new BaseTag(text, pos, pos + 1, false, null, l);
		return thisTag;
	}

	/**
	 * inserts a constructed html tag into a tab
	 * 
	 * @param t
	 *            - the current tab
	 * @param base
	 *            - the tag to insert
	 * @throws BadLocationException
	 *             - if the spot on the tab doesn't exist
	 */
	private void insertToTab(Tab t, BaseTag base) throws BadLocationException {
		int pos = t.getCaretLineNumber();
		if (t.head != null) {
			int indent = t.head.getIndentLevel(pos);
			t.head.addChild(base, pos);
			t.getDocument().insertString(t.getCaretPosition(),
					base.getText(indent), null);
		} else {
			ArrayList<BaseTag> b = new ArrayList<BaseTag>();
			b.add(base);
			t.head = new HTMLTag(b);
			t.getDocument().insertString(t.getCaretPosition(),
					t.head.getText(0), null);
		}
	}

	/**
	 * Inserts the tag into the document Puts cursor in middle of inserted tags
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Tab t = ew.getCurrentTab();
		// System.out.println(tag);
		try {
			if (tag.contains("table")) {
				insertTable(t);
			} else if (tag.equals("a")) {
				insertATag(t);
			} else if (tag.equals("img")) {
				insertImg(t);
			} else {
				insertToTab(t, makeNewTag(t, tag, null, this.selfClosing));
			}

		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * inserts a table into the tab
	 * 
	 * @param t
	 *            - the current tab
	 */
	private void insertTable(Tab t) {
		JPanel panel = new JPanel();
		JSpinner rows = new JSpinner(new SpinnerNumberModel(1, 1,
				Integer.MAX_VALUE, 1));
		JSpinner cols = new JSpinner(new SpinnerNumberModel(1, 1,
				Integer.MAX_VALUE, 1));
		panel.add(new JLabel("Rows: "));
		panel.add(rows);
		panel.add(new JLabel("Columns: "));
		panel.add(cols);
		JOptionPane pane = new JOptionPane(panel);
		pane.setSize(300, 150);
		@SuppressWarnings("static-access")
		int tableSize = pane.showOptionDialog(null, panel, "Table Size",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, null);
		if (tableSize == JOptionPane.OK_OPTION) {
			try {
				int row = (Integer) rows.getValue();
				int col = (Integer) cols.getValue();
				BaseTag base = makeNewTag(t, tag, null, this.selfClosing);
				for (; row > 0; row--) {
					BaseTag c1 = makeNewTag(t, "tr", null, this.selfClosing);
					for (int i = 0; i < col; i++) {
						c1.addChild(makeNewTag(t, "td", null, this.selfClosing));
					}
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
	 * 
	 * @param t
	 *            - the current tab
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
	 * 
	 * @param t
	 *            - current tab
	 */
	private void insertImg(final Tab t) {

		final String url = getImagePath();
		BaseTag base;
		if (url == null) {
			return;
		}
		base = makeNewTag(t, "img", url, this.selfClosing);

		try {
			insertToTab(t, base);
			t.addMouseListener(new ClickableImageSrc(url, t));

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * gets the path of an image
	 * 
	 * @return - the path of an image
	 */
	private String getImagePath() {
		JPanel p = new JPanel(new BorderLayout());
		final JTextArea t = new JTextArea(1, 30);

		final JFileChooser c = new JFileChooser();
		FileFilter imageFilter = new FileNameExtensionFilter("Image files",
				ImageIO.getReaderFileSuffixes());
		c.addChoosableFileFilter(imageFilter);

		JButton b = new JButton("Browse");
		b.addActionListener(new ActionListener() {
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
		p.add(b, BorderLayout.EAST);

		int choice = JOptionPane.showConfirmDialog(null, p, "Choose Source", 0);

		if (choice == JOptionPane.YES_OPTION) {
			return t.getText();
		}
		return null;

	}

	@Override
	public boolean addEdit(UndoableEdit arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRedo() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUndo() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPresentationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRedoPresentationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUndoPresentationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSignificant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void redo() throws CannotRedoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean replaceEdit(UndoableEdit arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void undo(int lineNum) throws CannotUndoException {
		// TODO Auto-generated method stub
		this.thisTag.remove(lineNum);
	}

	@Override
	public void undo() throws CannotUndoException {
		// TODO Auto-generated method stub
		
	}
}
