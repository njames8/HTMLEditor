package ui;
import javax.swing.*;

import commands.OpenFile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorWindow extends javax.swing.JFrame {
	private JTextField textField;
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
		
		/*
		 * File
		 */
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open File");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: make this a command
				OpenFile o = new OpenFile(new JFileChooser(), EditorWindow.this);
				o.execute();
			}
		});
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		mnFile.add(mntmOpen);
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
		
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		textField = new JTextField();
		tabbedPane.addTab("New tab", null, textField, null);
		textField.setColumns(10);
		
		
	}

	private Tab makeTextPanel(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
