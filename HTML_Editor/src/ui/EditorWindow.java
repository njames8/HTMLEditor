package ui;
import javax.swing.*;

import cmd.*;
import files.HTMLFile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class EditorWindow extends javax.swing.JFrame {
	private static final int MAXIMUM_TABS = 10;
	private JTabbedPane tabbedPane;
	private Tab[] tabs = new Tab[MAXIMUM_TABS];

	
	public EditorWindow() {
		init();
	}
	
	private void init() {
		setTitle("HTML Editor");
		setMinimumSize(new Dimension(320, 240));
		setSize(640, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Courier New", Font.PLAIN, 11));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		NewTab(tabbedPane);
		
		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
				
		/*
		 * File
		 */
		JMenu mnFile = new JMenu("File");

		JMenuItem mntmNew = new JMenuItem("New");//Makes the menu button for making a new tab
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewTab(tabbedPane);//Adds new tab to the window
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open File");//Makes the menu button for opening files
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//calls the command to open file and adds it to the window.
				OpenFile o = new OpenFile(new JFileChooser(), EditorWindow.this, tabbedPane);
				o.execute();
			}
		});
		
		JMenuItem mntmClose = new JMenuItem("Close");//Makes the close button for closing the window. 
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Close c = new Close();//Makes the command to close
				c.execute();
			}
		});
		
		JMenuItem mntmSave = new JMenuItem("Save");//Makes the Save button for saving files
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				//finds the current tab
				//Makes the command to save the file in the current tab.
				SaveFile f = new SaveFile((Tab)(tabbedPane.getComponentAt(tabbedPane.getSelectedIndex())), EditorWindow.this);
				f.execute();
			}
		});
		
		JMenuItem mntmCloseTab = new JMenuItem("Close Tab");//Makes the button for closing the current tab.
		mntmCloseTab.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Makes the command to close the current tab.
				CloseTab t = new CloseTab(tabbedPane.getComponentAt(tabbedPane.getSelectedIndex()),tabbedPane);
				t.execute();
			}
		});
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");//Makes the Save button for saving files
		
		/*
		 * Edit
		 */
		JMenu mnEdit = new JMenu("Edit");//makes the edit menu 
		/*
		 * View
		 */
		JMenu mnView = new JMenu("View");//makes the view menu
		
		//adds all the menu buttons and menu headers to the window.
		menuBar.add(mnFile);
			mnFile.add(mntmNew);
			mnFile.add(mntmOpen);
			mnFile.add(mntmSave);
			mnFile.add(mntmSaveAs);
			mnFile.add(mntmCloseTab);
			mnFile.add(mntmClose);
		menuBar.add(mnEdit);
		menuBar.add(mnView);
		
	}


	public void NewTab(JTabbedPane j) {
		NewTab(new HTMLFile(), j );
	}

	public void NewTab(HTMLFile file, JTabbedPane j) {
		Tab tab = new Tab(file, j);
		
		
		for (int i = 0; i < 10; i++){
			if(tabs[i] == null){
				tabs[i] = tab;
				break;
			}else if(i == 9){
				MessageBox warn = new MessageBox("Too Many Tabs",
						"You have too many tabs open, please close some before continuing.",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		tabbedPane.addTab(tab.getFile().GetTabName(), null, tab, null);
	}
}
