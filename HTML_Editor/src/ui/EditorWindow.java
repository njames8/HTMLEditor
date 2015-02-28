package ui;
import javax.swing.*;

import cmd.*;
import files.HTMLFile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorWindow extends javax.swing.JFrame {
	private JTabbedPane tabbedPane;
	private Tab[] tabs = new Tab[10];
	public EditorWindow() {
		init();
	}
	
	private void init() {
		setTitle("HTML Editor");
		setMinimumSize(new Dimension(320, 240));
		setSize(640, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		NewTab();
		
		/*
		 * File
		 */
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*NewFile n = new NewFile(new JFileChooser(), EditorWindow.this);
				n.execute();*/
				NewTab();
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open File");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenFile o = new OpenFile(new JFileChooser(), EditorWindow.this);
				o.execute();
			}
		});
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Close c = new Close();
				c.execute();
			}
		});
		
		JMenuItem mntmNewFile = new JMenuItem("New File");
		mntmNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		mnFile.add(mntmNew);
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
/*
 * not sure if this is the best way to save the file.
 * I have not made the SaveTab class anywhere.
 * 
 * Matthew Gallagher
 */
				SaveFile f = new SaveFile(new Tab());//TODO Pass current tab
				f.execute();
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmCloseTab = new JMenuItem("Close Tab");
		mntmCloseTab.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				CloseTab t = new CloseTab(new Tab());// TODO Pass current tab
//				t.execute();
			}
		});
		mnFile.add(mntmCloseTab);
		mnFile.add(mntmClose);
		
		/*
		 * Edit
		 */
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		/*
		 * View
		 */
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);	
	}
	
	public void NewTab() {
		NewTab(new HTMLFile());
	}
	
	public void NewTab(HTMLFile file) {
		Tab tab = new Tab(file);
		tabbedPane.addTab(tab.getFile().GetTabName(), null, tab, null);
	}
}
