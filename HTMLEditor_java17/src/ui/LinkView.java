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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import formatting.Link;
/**
 * 
 * @author nick james
 *
 */
@SuppressWarnings("serial")
public class LinkView extends JPanel {

	private JTextArea linkArea;
	private Tab t;
	private ArrayList<Link> links;
	private JMenuBar menu;

	/**
	 * 
	 */
	public LinkView(Tab t) {
		this.t = t;
		this.linkArea = null;
	}
	
	/**
	 * 
	 * @param links
	 * @param t
	 */
	public LinkView(ArrayList<Link> links, Tab t) {
		this.links = links;
		makeView();
	}
	
	/**
	 * Makes the LinkView UI
	 */
	public void makeView() {
		this.links = getLinks();
		Dimension d = new Dimension(t.getWidth(),
				t.getHeight() / 3);
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
	
	/**
	 * gets the links in the text
	 * @return - Arraylist of the links in the text
	 */
	private ArrayList<Link> getLinks() {
		String text = this.t.getText();
		ArrayList<Link> temp = new ArrayList<Link>();
		if (text != null && text.length() > 0) {
			String parts[] = text.split("(<[^>a]*>)|(<a href=\")|(\">)|(</a>)");
			for (int i = 0; i < parts.length; i++) {
				if (parts[i] != null && parts[i].length() != 0 && (int) parts[i].charAt(0) != 10) {
					temp.add(new Link(parts[i]));
				}
			}
		}
		return temp;
	}
	
	/**
	 * sets the text of the link view area
	 */
	private void setText() {
		String text = "";
		for (Link l : this.links) {
			text += l.getLink() + "\n";
		}
		linkArea.setText(text);
	}
	
	/**
	 * Updates the linkview with links
	 */
	public void updateLinks() {
		if (this.linkArea == null) {
			makeView();
		}
		this.links = getLinks();
		this.setText();

	}
	
	/**
	 * initializes the linkview menubar
	 */
	private void initMenu() {
		this.menu = new JMenuBar();
		Dimension d = new Dimension(this.getWidth(), (int) menu
				.getPreferredSize().getHeight() + 20);
		this.menu.setPreferredSize(d);
		this.menu.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.menu.setBackground(UIManager.getLookAndFeelDefaults().getColor(
				"Menu.background"));
		this.menu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JMenuItem x = new JMenuItem();
		x.addActionListener(new ActionListener() {
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
		refresh.addActionListener(new ActionListener() {
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
	
	public void sortLinksAZ(){
		mergeSort(links);
	}
	
	private ArrayList<Link> mergeSort(ArrayList<Link> l){
		if (l.size() <= 1){
			return l;
		}
		else{
			ArrayList<Link> left = new ArrayList<Link>();
			ArrayList<Link> right = new ArrayList<Link>(); 
			ArrayList<Link> result;
			int middle = l.size()/2;
			
			for (int x=0; x < middle-1; x++){
				left.add(l.get(x));
			}
			for (int x=middle; x < l.size(); x++){
				right.add(l.get(x));
			}
			mergeSort(left);
			mergeSort(right);
			int lLast = left.size()-1;
			if (left.get(lLast).compareTo(right.get(0))<=0){
				right.add(left.get(lLast));
				left.remove(lLast);
				return left;
			}
			result = merge(left,right);
			return result;
		}
	}
	private ArrayList<Link> merge(ArrayList<Link> left, ArrayList<Link> right){
		ArrayList<Link> list;
		ArrayList<Link> result = new ArrayList<Link>();
		return null;
	}
	

}
