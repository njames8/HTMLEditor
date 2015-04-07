package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		String text = "";
		for (Link l : this.links){
			text += l.getLink() + "\n";
		}
		
		linkArea.setText(text);
		this.setSize(EditorWindow.getInstance().getWidth(), EditorWindow.getInstance().getHeight()/5);
		linkArea.setSize(this.getSize());
		JScrollPane sp = new JScrollPane(linkArea);
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
