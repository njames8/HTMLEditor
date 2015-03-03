/**
 * 
 */
package ui;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

import cmd.*;
import files.HTMLFile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

/**
 * The Application GUI
 * 
 * @author Nick James
 *
 */
public class EditorWindow extends javax.swing.JFrame {
	/**
	 * Maximum number of tabs that can be open at once
	 */
	private static final int MAXIMUM_TABS = 10;

	/**
	 * The area to hold tabs
	 */
	private JTabbedPane tabbedPane;

	/**
	 * collection of tabs
	 */
	private Tab[] tabs = new Tab[MAXIMUM_TABS];

	/**
	 * Constructs the window
	 */
	public EditorWindow() {
		init();
	}

	/**
	 * Initializes the window: Sets dimensions Sets how to close generates an
	 * area for tabs adds a New File tab sets up the menu and menu items
	 * 
	 */
	private void init() {

		setTitle("HTML Editor");
		setMinimumSize(new Dimension(320, 240));
		setSize(640, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Consolas", Font.PLAIN, 11));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		NewTab(tabbedPane);

		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		/*
		 * File
		 */
		JMenu mnFile = new JMenu("File");

		JMenuItem mntmNew = new JMenuItem("New");// Makes the menu button for
													// making a new tab
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewTab(tabbedPane);// Adds new tab to the window
			}
		});

		JMenuItem mntmOpen = new JMenuItem("Open File");// Makes the menu button
														// for opening files
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// calls the command to open file and adds it to the window.
				OpenFile o = new OpenFile(EditorWindow.this, tabbedPane);
				o.execute();
			}
		});

		JMenuItem mntmClose = new JMenuItem("Close");// Makes the close button
														// for closing the
														// window.
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Close c = new Close(tabbedPane);// Makes the command to close
				c.execute();
			}
		});

		JMenuItem mntmSave = new JMenuItem("Save");// Makes the Save button for
													// saving files
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// finds the current tab
				// Makes the command to save the file in the current tab.
				SaveFile f = new SaveFile((Tab) tabbedPane.getSelectedComponent());
				f.execute();
			}
		});

		JMenuItem mntmCloseTab = new JMenuItem("Close Tab");// Makes the button
															// for closing the
															// current tab.
		mntmCloseTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Makes the command to close the current tab.
				CloseTab t = new CloseTab(tabbedPane.getSelectedComponent(), tabbedPane);
				t.execute();
			}
		});

		JMenuItem mntmSaveAs = new JMenuItem("Save As...");// Makes the Save
															// button for saving
															// files
		mntmSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SaveAsFile a = new SaveAsFile((Tab) tabbedPane
						.getSelectedComponent());
				a.execute();
			}

		});

		/*
		 * Edit
		 */
		JMenu mnEdit = new JMenu("Edit");// makes the edit menu

		JMenu mnInsert = new JMenu("Insert");// makes the insert menu

		JMenu mnHtmlTag = new JMenu("Tag");

		JMenuItem table = new JMenuItem("Table");

		JMenuItem html = new JMenuItem("HTML");
		JMenuItem body = new JMenuItem("Body");
		JMenuItem paragraph = new JMenuItem("Paragraph");
		JMenuItem bold = new JMenuItem("Bold");
		JMenuItem italic = new JMenuItem("Italic");

		html.addActionListener(new TagListener(html.getText(), (Tab) tabbedPane
				.getSelectedComponent()));
		body.addActionListener(new TagListener(body.getText(), (Tab) tabbedPane
				.getSelectedComponent()));
		paragraph
				.addActionListener(new TagListener(paragraph.getText(),
						(Tab) tabbedPane.getComponentAt(tabbedPane
								.getSelectedIndex())));
		bold.addActionListener(new TagListener(bold.getText(), (Tab) tabbedPane
				.getSelectedComponent()));
		italic.addActionListener(new TagListener(italic.getText(),
				(Tab) tabbedPane.getSelectedComponent()));

		
		JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());
		cut.setText("Cut");
		
		

		JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		copy.setText("Copy");
		

		JMenuItem paste = new JMenuItem(new Paste((Tab)tabbedPane.getSelectedComponent(), tabbedPane));
		paste.setText("Paste");
		paste.setMnemonic(KeyEvent.VK_P);
		
		JMenu mnList = new JMenu("List");
		
		JMenuItem ordered = new JMenuItem("Ordered");
		ordered.addActionListener(new TagListener(ordered.getText(), (Tab)tabbedPane.getSelectedComponent()));
		
		JMenuItem unordered = new JMenuItem("Unordered");
		unordered.addActionListener(new TagListener(unordered.getText(), (Tab)tabbedPane.getSelectedComponent()));
		
		// adds all the menu buttons and menu headers to the window.
		menuBar.add(mnFile);
			mnFile.add(mntmNew);
			mnFile.add(mntmOpen);
			mnFile.add(mntmSave);
			mnFile.add(mntmSaveAs);
			mnFile.add(mntmCloseTab);
			mnFile.add(mntmClose);
		menuBar.add(mnEdit);
			mnEdit.add(cut);
			mnEdit.add(copy);
			mnEdit.add(paste);
		menuBar.add(mnInsert);
			mnInsert.add(mnHtmlTag);
				mnHtmlTag.add(html);
				mnHtmlTag.add(body);
				mnHtmlTag.add(paragraph);
				mnHtmlTag.add(bold);
				mnHtmlTag.add(italic);
			mnInsert.add(mnList);
				mnList.add(ordered);
				mnList.add(unordered);
			mnInsert.add(table);

		
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                //frame.dispose();
            	Close c = new Close(tabbedPane);
            	c.execute();
            }
        });
        

	}

	/**
	 * Adds a tab to the window with a new file
	 * 
	 * @param j
	 *            - area for tabs
	 */
	public void NewTab(JTabbedPane j) {
		NewTab(new HTMLFile(), j);
	}

	/**
	 * Adds a tab to the window with a file attached If there are too many tabs
	 * open, presents error message
	 * 
	 * @param file
	 *            - file to be attached to tab
	 * @param j
	 *            - The area of tabs
	 */
	public void NewTab(HTMLFile file, JTabbedPane j) {
		Tab tab = new Tab(file);
		tab.setFont(new Font("Consolas", Font.PLAIN, 11));
		for (int i = 0; i < 10; i++) {
			if (tabs[i] == null) {
				tabs[i] = tab;
				break;
			} else if (i == 9) {
				MessageBox warn = new MessageBox(
						"Too Many Tabs",
						"You have too many tabs open, please close some before continuing.",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		tabbedPane.addTab(tab.getFile().GetTabName(), null, tab, null);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
	}
}
