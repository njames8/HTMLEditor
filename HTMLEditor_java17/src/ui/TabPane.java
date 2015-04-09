/**
 * 
 */
package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author nick
 *
 */
public class TabPane extends JPanel{
	private Tab t;
	private LinkView lv;
	
	public TabPane(Tab t){
		super();
		this.t = t;
		this.lv = new LinkView(t);
		this.setLayout(new BorderLayout());
		JScrollPane sp = new JScrollPane(t);
		this.add(sp, BorderLayout.CENTER);
		this.add(lv, BorderLayout.SOUTH);
	}
	
	public TabPane(Tab t, LinkView lv){
		super();
		this.t = t;
		this.lv = lv;
		this.setLayout(new BorderLayout());
		JScrollPane sp = new JScrollPane(t);
		this.add(sp, BorderLayout.CENTER);
		this.add(lv, BorderLayout.SOUTH);
	}
	
	public void showLinkView(){
		this.lv.updateLinks();
		this.lv.setVisible(true);
	}
	
	public void hideLinkView(){
		this.lv.setVisible(false);
	}
	
	public Tab getTab(){
		return this.t;
	}
}
