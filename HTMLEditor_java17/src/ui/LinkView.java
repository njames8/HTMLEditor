package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import formatting.Link;

public class LinkView extends JPanel{
	
	private JTextArea linkArea;
	private Tab t;
	
	public LinkView(ArrayList<Link> links, Tab t){
		this.linkArea = new JTextArea();
		for (Link l : links){
			this.linkArea.insert(l.getLink()+"\n", 0);
		}
		
		this.add(linkArea);
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
