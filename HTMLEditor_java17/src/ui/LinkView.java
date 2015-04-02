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
		this.linkArea = new JTextArea();
		this.links = links;
		for (Link l : links){
			this.linkArea.insert(l.getLink()+"\n", 0);
		}
		
		this.frame = new JFrame();
		this.frame.add(linkArea);
	}
	
	public LinkView(Tab t){
		this.t = t;
		traverseTab();
	}
	
	private ArrayList<Link> traverseTab(){
		String text = this.t.getText();
		//TODO fix split
		String parts[] = text.split("(href=[\"\']).*([\'\"])");
		System.out.println(parts[0]);
		return null;
	}
	
	
}
