package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import formatting.Link;

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
		this.linkArea = new JTextArea();	
		this.add(linkArea);
		String text = "";
		for (Link l : this.links){
			text += l.getLink() + "\n";
		}
		linkArea.setText(text);
	}
	
	private ArrayList<Link> getLinks(){
		String text = this.t.getText();
		ArrayList<Link> temp = new ArrayList<Link>();
		String parts[] = text.split("\\s*<a\\s*href\\s*=\\s*\"|\">(.*)</a>");
		for (int i = 0; i < parts.length; i++) {
			if (parts[i]!=null){
				temp.add(new Link(parts[i]));
			}
		}
		return temp;
	}
	
	
}
