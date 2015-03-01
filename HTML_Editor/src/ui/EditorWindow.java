package ui;
import javax.swing.*;

import cmd.*;
import files.HTMLFile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

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
		
		NewTab(tabbedPane);
		
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
				NewTab(tabbedPane);
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open File");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenFile o = new OpenFile(new JFileChooser(), EditorWindow.this, tabbedPane);
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
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * not sure if this is the best way to save the file. I have not
				 * made the SaveTab class anywhere.
				 * 
				 * Matthew Gallagher
				 */
				SaveFile f = new SaveFile(new Tab());// TODO Pass current tab
				Tab t = new Tab();
				for(int i = 0; i < 10; i++){
					if (tabs[i].getFocus() == true){
						t = tabs[i];
					}
				}
			}
		});
	
		mnFile.add(mntmSave);
		
		JMenuItem mntmCloseTab = new JMenuItem("Close Tab");
		mntmCloseTab.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(tabbedPane.getSelectedIndex());
				CloseTab t = new CloseTab(tabbedPane.getComponentAt(tabbedPane.getSelectedIndex()),tabbedPane);// TODO Pass current tab
				t.execute();

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
				//TODO need to send error that there are to many tabs open
			}
		}
		tabbedPane.addTab(tab.getFile().GetTabName(), null, tab, null);
	}
}
