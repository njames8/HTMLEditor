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
import java.util.LinkedList;
import java.util.List;

/**
 * The Application GUI
 * 
 * @author Nick James
 * @author Matthew Gallagher
 * @author Adam Walsh
 * @author Jesse Roux
 * @author John Mullen
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
	 * collection of tabs, stored as a linked list
	 */
	private List<Tab> tabs;
	
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
		
		// Create a blank tab for the user to start with
        NewTab();
	}

	/**
	 * Initializes the window: Sets dimensions Sets how to close generates an
	 * area for tabs adds a New File tab sets up the menu and menu items
	 * 
	 */
	private void init() {
		// Set the window size, position and title
		setTitle("HTML Editor");
		setMinimumSize(new Dimension(320, 240));
		setSize(640, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		// Init tabs and add the JTabbedPane to our window
		tabs = new LinkedList<Tab>();
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		
		// Setup the menus
		initMenus();
		
		// Execute the Close command when the window is closed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                //frame.dispose();
            	new Close().execute();
            }
        });
        
	}
	
	private void initMenus() {
		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Init and add menus
		initFileMenu(menuBar);
		initEditMenu(menuBar);
		initInsertMenu(menuBar);
		initFormatMenu(menuBar);
	}
	
	/**
	 * Initializes and adds the file menu to the menu bar
	 * 
	 * @param menu The JMenuBar that the menu items will be added to
	 */
	private void initFileMenu(JMenuBar menu) {
		JMenu mnFile = new JMenu("File");

		// New File (CTRL + N)
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_DOWN_MASK));
		
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewTab();// Adds new tab to the window
			}
		});

		// Open File (CTRL + O)
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
		
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// calls the command to open file and adds it to the window.
				OpenFileDialog o = new OpenFileDialog(EditorWindow.this, tabbedPane);
				o.execute();
			}
		});
		
		// Close (CTRL + W)
		JMenuItem mntmCloseTab = new JMenuItem("Close");
		mntmCloseTab.setAccelerator(KeyStroke.getKeyStroke('W', KeyEvent.CTRL_DOWN_MASK));
		
		mntmCloseTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Tab t = EditorWindow.getInstance().getCurrentTab();

				new CloseTab(t, tabbedPane).execute();
			}
		});

		// Close All (CTRL + Shift + W)
		JMenuItem mntmCloseAll = new JMenuItem("Close All");
		mntmCloseAll.setAccelerator(KeyStroke.getKeyStroke('W', KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
		
		mntmCloseAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CloseAllTab().execute();
			}
		});

		// Save (CTRL + S)
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
		
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tab t = EditorWindow.getInstance().getCurrentTab();
				
				SaveFile f = new SaveFile(t);
				f.execute();
				//System.out.println(t.GetTitle());
				//tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), t.GetTitle());
			}
		});

		// Save As... (CTRL + Shift + S)
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
		
		mntmSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Tab t = EditorWindow.getInstance().getCurrentTab();
				
				new SaveAsFile(t).execute();
			}

		});
		
		// Exit
		JMenuItem mntmExit = new JMenuItem("Exit"); 
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Close().execute();
			}
		});
		
		mnFile.add(mntmNew);
		mnFile.add(mntmOpen);
		mnFile.addSeparator(); // --------
		mnFile.add(mntmCloseTab);
		mnFile.add(mntmCloseAll);
		mnFile.addSeparator(); // --------
		mnFile.add(mntmSave);
		mnFile.add(mntmSaveAs);
		mnFile.addSeparator(); // --------
		mnFile.add(mntmExit);
		
		menu.add(mnFile);
	}
	
	/**
	 * Initializes and adds the edit menu to the menu bar
	 * 
	 * @param menu The JMenuBar that the menu items will be added to
	 */
	private void initEditMenu(JMenuBar menu) {
		JMenu mnEdit = new JMenu("Edit");
		
		// Cut (CTRL + X)
		JMenuItem cut = new JMenuItem(new Cut());
		cut.setText("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke('X', KeyEvent.CTRL_DOWN_MASK));

		// Copy (CTRL + C)
		JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		copy.setText("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_DOWN_MASK));
		
		// Paste (CTRL + V)
		JMenuItem paste = new JMenuItem(new Paste());
		paste.setText("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_DOWN_MASK));
		
		// Select All (CTRL + A)
		JMenuItem selectAll = new JMenuItem("Select All");
		selectAll.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_DOWN_MASK));
		
		selectAll.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Tab t = EditorWindow.getInstance().getCurrentTab();
					
					t.selectAll();
				}
			}
		);
		
		
		////////////////////////////////////////
		// Preferences sub menu
		////////////////////////////////////////
		JMenu mnPreferences = new JMenu("Preferences");
		
		//Auto Word-Wrapping
		JMenuItem wordWrapToggle = new JMenuItem("Auto Word-Wrapping");
		wordWrapToggle.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						Tab t = EditorWindow.getInstance().getCurrentTab();
						t.setLineWrap(false);
					}
				});
		//Auto Indentation: NOT YET IMPLEMENTED
		JMenuItem indentToggle = new JMenuItem("Auto Indentation");
		indentToggle.addActionListener(null);
		
		menu.add(mnEdit);
		
		mnEdit.add(cut);
		mnEdit.add(copy);
		mnEdit.add(paste);
		mnEdit.addSeparator(); // --------
		mnEdit.add(selectAll);
		mnEdit.addSeparator(); // --------
		mnEdit.add(mnPreferences);
		
		mnPreferences.add(wordWrapToggle);
		mnPreferences.add(indentToggle);
	
	}
	
	/**
	 * Initializes and adds the insert menu to the menu bar
	 * 
	 * @param menu The JMenuBar that the menu items will be added to
	 */
	private void initInsertMenu(JMenuBar menu) {
		JMenu mnInsert = new JMenu("Insert");

		////////////////////////////////////////
		// Tag sub menu
		////////////////////////////////////////
		JMenu mnHtmlTag = new JMenu("Tag");
		
		// <html>
		JMenuItem html = new JMenuItem("HTML");
		html.addActionListener(new InsertTag("html"));
		
		// <head>
		JMenuItem head = new JMenuItem("Head");
		head.addActionListener(new InsertTag("head"));
		
		// <body>
		JMenuItem body = new JMenuItem("Body");
		body.addActionListener(new InsertTag("body"));
		
		// <p>
		JMenuItem paragraph = new JMenuItem("Paragraph");
		paragraph.addActionListener(new InsertTag("p"));
		
		// <b> (CTRL + B)
		JMenuItem bold = new JMenuItem("Bold");
		bold.addActionListener(new InsertTag("b"));
		bold.setAccelerator(KeyStroke.getKeyStroke('B', KeyEvent.CTRL_DOWN_MASK));
		
		// <i> (CTRL + i)
		JMenuItem italic = new JMenuItem("Italic");
		italic.addActionListener(new InsertTag("i"));
		italic.setAccelerator(KeyStroke.getKeyStroke('I', KeyEvent.CTRL_DOWN_MASK));
		
		// <img>
		JMenuItem img = new JMenuItem("Image");
		img.addActionListener(new CopyOfInsertTag("img", true));
		
		// <a>
		JMenuItem hyperlink = new JMenuItem("HyperLink (a)");
		hyperlink.addActionListener(new CopyOfInsertTag("a"));
		
		////////////////////////////////////////
		// List sub menu
		////////////////////////////////////////
		JMenu mnList = new JMenu("List");
		
		JMenuItem ordered = new JMenuItem("Ordered");
		ordered.addActionListener(new InsertTag("ol"));
		
		JMenuItem unordered = new JMenuItem("Unordered");
		unordered.addActionListener(new InsertTag("ul"));

		JMenuItem listItem = new JMenuItem("List Item");
		listItem.addActionListener(new InsertTag("li"));
		
		////////////////////////////////////////
		// Table
		////////////////////////////////////////
		JMenuItem table = new JMenuItem("Table");
		table.addActionListener(new InsertTag("table"));
		
		mnInsert.add(mnHtmlTag);
			mnHtmlTag.add(html);
			mnHtmlTag.add(head);
			mnHtmlTag.add(body);
			mnHtmlTag.add(paragraph);
			mnHtmlTag.add(bold);
			mnHtmlTag.add(italic);
			mnHtmlTag.add(img);
			mnHtmlTag.add(hyperlink);
			
			
		mnInsert.add(mnList);
			mnList.add(ordered);
			mnList.add(unordered);
			mnList.add(listItem);
			
		mnInsert.add(table);
			
		menu.add(mnInsert);
	}
	
	/**
	 * Initializes and adds the format menu to the menu bar
	 * 
	 * @param menu The JMenuBar that the menu items will be added to
	 */
	private void initFormatMenu(JMenuBar menu) {
		JMenu mnFormat = new JMenu("Format");
		
		// Validate HTML (CTRL + Enter)
		JMenuItem validate = new JMenuItem("Validate");
		validate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_DOWN_MASK));
		
		validate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JScrollPane temp = (JScrollPane)tabbedPane.getSelectedComponent();
				JViewport temp2 = temp.getViewport();
				Tab t = (Tab)temp2.getView();
				ParseCMD p = new ParseCMD(t);
				p.execute();
			}
		});
		
		mnFormat.add(validate);
		
		menu.add(mnFormat);
	}

	/**
	 * Adds a tab to the window with a new file
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
		Tab t = new Tab(file, text);
		t.addMouseListener(new RightClickListener());
		t.setFont(new Font("Consolas", Font.PLAIN, 11));
		
		// We are trying to add a new tab when we have the max number open already
		if(tabs.size() == MAXIMUM_TABS) {
			new MessageBox(
				"Too Many Tabs",
				"You have too many tabs open, please close some before continuing.",
				JOptionPane.WARNING_MESSAGE);
			
			return;
		}
		
		tabs.add(t);
		
		JScrollPane scrollPane = new JScrollPane(t);
		t.attachObserver(this);
		tabbedPane.addTab(t.getTitle(), null, scrollPane, null);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
	}
	
	public Tab getCurrentTab() {
		return tabs.get(tabbedPane.getSelectedIndex());
	}

	@Override
	public void update(Tab t) {
		tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), t.getTitle());
	}
	
	// Closes all tabs, triggering saves if needed
	public boolean closeAll() {
		for(Component c : tabbedPane.getComponents()) {
			tabbedPane.setSelectedComponent(c);
			if(!close()){
				return false;
			}
		}
		return true;
	}
	
	// Closes the currently selected tab
	public boolean close() {
		int index = tabbedPane.getSelectedIndex();

		// This may trigger a save dialog if there are unsaved changes

		if (tabs.get(index).close()) {

			// Remove the tab from our collection and the window
			tabs.remove(index);
			tabbedPane.remove(index);
			if (tabs.size() == 0) {
				NewTab();
			}
			return true;
		}
		return false;
	}
}
