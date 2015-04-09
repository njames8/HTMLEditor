package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import formatting.Link;

@SuppressWarnings("serial")
public class LinkView extends JPanel{
	
	private JTextArea linkArea;
	private Tab t;
	private ArrayList<Link> links;
	private JMenuBar menu;


	
	/**
	 * @wbp.parser.constructor
	 */
	public LinkView(Tab t){
		this.t = t;
		this.linkArea = null;
	}
		
	public LinkView(ArrayList<Link> links, Tab t){
		this.links = links;
		makeView();
	}
	
	public void makeView(){
		this.links = getLinks();
		Dimension d = new Dimension(EditorWindow.getInstance().getWidth(), EditorWindow.getInstance().getHeight()/3);
		this.setPreferredSize(d);
		
		this.linkArea = new JTextArea();	
		
		setText();
		linkArea.setSize(this.getSize());
		linkArea.setEditable(false);
		
		JScrollPane sp = new JScrollPane(linkArea);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		initMenu();
		
		this.setLayout(new BorderLayout());
		this.add(sp, BorderLayout.CENTER);
		this.add(menu, BorderLayout.NORTH);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
	}
	
	private ArrayList<Link> getLinks(){
		String text = this.t.getText();
		System.out.println(text);
		ArrayList<Link> temp = new ArrayList<Link>();
		if (text!=null && text.length()>0){
		String parts[] = text.split("(<[^>a]*>)|(<a href=\")|(\">)|(</a>)");
		for (int i = 0; i < parts.length; i++) {
			if (parts[i]!=null && parts[i].length()!=0 && (int)parts[i].charAt(0)!= 10){
				System.out.println(parts[i]);
				temp.add(new Link(parts[i]));
			}
		}
		}
		return temp;
	}
	
	private void setText(){
		String text = "";
		for (Link l : this.links){
			text += l.getLink() + "\n";
		}
		linkArea.setText(text);
	}
	
	public void updateLinks(){
		if (this.linkArea == null){
			makeView();
		}
		this.links = getLinks();
		this.setText();
		
	}
	private void initMenu(){
		this.menu = new JMenuBar();
		Dimension d = new Dimension(this.getWidth(), (int)menu.getPreferredSize().getHeight()+20);
		this.menu.setPreferredSize(d);
		this.menu.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.menu.setBackground(UIManager.getLookAndFeelDefaults().getColor("Menu.background"));
		this.menu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		JMenuItem x = new JMenuItem();
		x.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		Font menuFont = new Font("Monospaced", Font.PLAIN, 11);
		x.setText("Close");
		x.setFont(menuFont);
		
		JMenuItem refresh = new JMenuItem();
		refresh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				updateLinks();
			}
		});
		

		refresh.setText("Refresh");
		refresh.setFont(menuFont);
		this.menu.add(refresh);
		this.menu.add(x);
		
		
		
		
	}
	
	
}
