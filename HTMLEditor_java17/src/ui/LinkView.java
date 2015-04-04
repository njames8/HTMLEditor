package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import formatting.Link;

public class LinkView {
	
	private JFrame frame;
	private JTextArea linkArea;
	private ArrayList<Link> links;
	private Tab t;
	
	public LinkView(ArrayList<Link> links, Tab t){
		for (Link l : links){
			this.linkArea.insert(l.getLink()+"\n", 0);
		}
	}
	
	public LinkView(Tab t){
		this.t = t;
		this.links = getLinks();
	}
	
	public JFrame makeView(){
		this.linkArea = new JTextArea();
		
		this.frame = new JFrame();
		this.frame.add(linkArea);
		
		String text = "";
		for (Link l : this.links){
			text += l.getLink() + "\n";
		}
		linkArea.setText(text);
		
		return frame;
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
