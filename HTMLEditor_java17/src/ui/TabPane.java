/**
 * 
 */
package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * @author nick james
 * Hold the tab and the link view and splits the window into 2 views
 */
@SuppressWarnings("serial")
public class TabPane extends JPanel{
	/**
	 * The tab in this pane
	 */
	private Tab t;
	
	/**
	 * the LinkView in this pane
	 */
	private LinkView lv;
	
	/**
	 * Constructor
	 *   Creates and attaches a new LinkView
	 * @param t - the tab to attach
	 */
	public TabPane(Tab t){
		super();
		this.t = t;
		this.lv = new LinkView(t);
		this.setLayout(new BorderLayout());
		JScrollPane sp = new JScrollPane(t);
		this.add(sp, BorderLayout.CENTER);
		this.add(lv, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Constructor
	 * @param t - tab in this pane
	 * @param lv - link view in this pane
	 */
	public TabPane(Tab t, LinkView lv){
		super();
		this.t = t;
		this.lv = lv;
		this.setLayout(new BorderLayout());
		JScrollPane sp = new JScrollPane(t);
		this.add(sp, BorderLayout.CENTER);
		this.add(lv, BorderLayout.SOUTH);
	}
	
	/**
	 * shows the link view
	 */
	public void showLinkView(){
		this.lv.updateLinks();
		this.lv.setVisible(true);
	}
	
	/**
	 * hides the LinkView
	 */
	public void hideLinkView(){
		this.lv.setVisible(false);
	}
	
	/**
	 * returns the tab in this pane
	 * @return - the tab in this pane
	 */
	public Tab getTab(){
		return this.t;
	}
	
	
}
