package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import formatting.Link;

@SuppressWarnings("serial")
public class LinkView extends JPanel{
	
	private JTextArea linkArea;
	private Tab t;
	private ArrayList<Link> links;

	
	public LinkView(Tab t){
		this.t = t;
		this.links = getLinks();
		makeView();
	}
		
	public LinkView(ArrayList<Link> links, Tab t){
		this.links = links;
		makeView();
	}
	
	public void makeView(){
		Dimension d = new Dimension(EditorWindow.getInstance().getWidth(), EditorWindow.getInstance().getHeight()/5);
		this.setPreferredSize(d);
		
		this.linkArea = new JTextArea();	
		String text = "";
		for (Link l : this.links){
			text += l.getLink() + "\n";
		}
		linkArea.setText(text);
		linkArea.setSize(this.getSize());
		linkArea.setEditable(false);
		
		JScrollPane sp = new JScrollPane(linkArea);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.setLayout(new BorderLayout());
		this.add(sp, BorderLayout.CENTER);
		
		
	}
	
	private ArrayList<Link> getLinks(){
		String text = this.t.getText();
		System.out.println(text);
		ArrayList<Link> temp = new ArrayList<Link>();
		String parts[] = text.split("(<[^>a]*>)|(<a href=\")|(\">)|(</a>)");
		for (int i = 0; i < parts.length; i++) {
			if (parts[i]!=null && parts[i].length()!=0 && (int)parts[i].charAt(0)!= 10){
				System.out.println(parts[i]);
				temp.add(new Link(parts[i]));
			}
		}
		return temp;
	}
	
	
}
