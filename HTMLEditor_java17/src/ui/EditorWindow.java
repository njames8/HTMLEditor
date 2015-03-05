/**
 * 
 */
package ui;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

import cmd.*;
import files.HTMLFile;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

/**
 * The Application GUI
 * 
 * @author Nick James
 *
 */
@SuppressWarnings("serial")
public class EditorWindow extends javax.swing.JFrame implements Observer {
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

	private static EditorWindow singleton = null;
	
	public static EditorWindow getInstance() {
		if(singleton == null) {
			singleton = new EditorWindow();
		}
		
		return singleton;
	}
	
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
		getContentPane().setLayout(new BorderLayout(0, 0));


		tabbedPane = new JTabbedPane();
		//tabbedPane.setFont();
		getContentPane().add(tabbedPane);
		NewTab();

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
				NewTab();// Adds new tab to the window
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

		JMenuItem mntmExit = new JMenuItem("Exit");// Makes the close button
														// for closing the
														// window.
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Close().execute();
			}
		});

		JMenuItem mntmSave = new JMenuItem("Save");// Makes the Save button for
													// saving files
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditorWindow ew = EditorWindow.getInstance();
				Tab t = (Tab)ew.tabbedPane.getSelectedComponent();
				
				SaveFile f = new SaveFile(t);
				f.execute();
				//System.out.println(t.GetTitle());
				//tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), t.GetTitle());
			}
		});

		JMenuItem mntmCloseTab = new JMenuItem("Close Tab");// Makes the button
															// for closing the
															// current tab.
		mntmCloseTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Makes the command to close the current tab.
				JScrollPane temp = (JScrollPane)tabbedPane.getSelectedComponent();
				JViewport temp2 = temp.getViewport();
				Tab tab = (Tab)temp2.getView();
				CloseTab t = new CloseTab(tab, tabbedPane);
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
				JScrollPane temp = (JScrollPane)tabbedPane.getSelectedComponent();
				JViewport temp2 = temp.getViewport();
				Tab t = (Tab)temp2.getView();
				SaveAsFile a = new SaveAsFile(t);
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
		JMenuItem head = new JMenuItem("Head");
		JMenuItem body = new JMenuItem("Body");
		JMenuItem paragraph = new JMenuItem("Paragraph");
		JMenuItem bold = new JMenuItem("Bold");
		JMenuItem italic = new JMenuItem("Italic");

		html.addActionListener(new InsertTag("html"));
		
		head.addActionListener(new InsertTag("head"));
		
		body.addActionListener(new InsertTag("body"));
		
		paragraph.addActionListener(new InsertTag("p"));
		
		bold.addActionListener(new InsertTag("b"));
		
		italic.addActionListener(new InsertTag("i"));

		
		JMenuItem cut = new JMenuItem(new Cut());
		cut.setText("Cut");
		
		

		JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		copy.setText("Copy");
		

		JMenuItem paste = new JMenuItem(new Paste());
		paste.setText("Paste");
		paste.setMnemonic(KeyEvent.VK_P);
		
		JMenu mnList = new JMenu("List");
		
		JMenuItem ordered = new JMenuItem("Ordered");
		ordered.addActionListener(new InsertTag("ol"));
		
		JMenuItem unordered = new JMenuItem("Unordered");

		unordered.addActionListener(new InsertTag("ul"));

		
		JMenuItem listItem = new JMenuItem("List Item");
		listItem.addActionListener(new InsertTag("li"));
		
		
				
		JMenuItem mnValidate = new JMenuItem("Validate");
		mnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JScrollPane temp = (JScrollPane)tabbedPane.getSelectedComponent();
				JViewport temp2 = temp.getViewport();
				Tab t = (Tab)temp2.getView();
				ParseCMD p = new ParseCMD(t);
				p.execute();
			}
		});
	
		// adds all the menu buttons and menu headers to the window.
		menuBar.add(mnFile);
			mnFile.add(mntmNew);
			mnFile.add(mntmOpen);
			mnFile.add(mntmSave);
			mnFile.add(mntmSaveAs);
			mnFile.add(mntmCloseTab);
			mnFile.add(mntmExit);
			
		menuBar.add(mnEdit);
			mnEdit.add(cut);
			mnEdit.add(copy);
			mnEdit.add(paste);
		
		menuBar.add(mnInsert);
		
			mnInsert.add(mnHtmlTag);
				mnHtmlTag.add(html);
				mnHtmlTag.add(head);
				mnHtmlTag.add(body);
				mnHtmlTag.add(paragraph);
				mnHtmlTag.add(bold);
				mnHtmlTag.add(italic);
				
				
			mnInsert.add(mnList);
				mnList.add(ordered);
				mnList.add(unordered);
				mnList.add(listItem);
				
			mnInsert.add(table);
		menuBar.add(mnValidate);
		
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                //frame.dispose();
            	new Close().execute();
            }
        });
	}

	/**
	 * Adds a tab to the window with a new file
	 * 
	 * @param j
	 *            - area for tabs
	 */
	public void NewTab() {
		NewTab(new HTMLFile(), "");
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
	public void NewTab(HTMLFile file, String text) {
		Tab tab = new Tab(file, text);
		tab.addMouseListener(new RightClickListener());
		tab.setFont(new Font("Consolas", Font.PLAIN, 11));
		
		for (int i = 0; i < MAXIMUM_TABS; i++) {
			if (tabs[i] == null) {
				tabs[i] = tab;
				break;
			} else if (i == MAXIMUM_TABS-1) {
				new MessageBox(
					"Too Many Tabs",
					"You have too many tabs open, please close some before continuing.",
					JOptionPane.WARNING_MESSAGE);
			}
		}
		
		//JScrollPane scrollPane = new JScrollPane(tab);
		tab.attachObserver(this);
		tabbedPane.addTab(tab.GetTitle(), null, tab, null);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
	}
	
	public Tab getCurrentTab() {
		return (Tab)tabbedPane.getSelectedComponent();
	}

	@Override
	public void update(Tab t) {
		int index = tabbedPane.indexOfComponent(t);
		
		if(index == -1) {
			// this shouldn't happen
		} else {
			tabbedPane.setTitleAt(index, t.GetTitle());
		}
	}
	
	// Closes all tabs, triggering saves if needed
	public void close() {
		for(Component c : tabbedPane.getComponents()) {
			((Tab)c).close();
		}
	}
}
