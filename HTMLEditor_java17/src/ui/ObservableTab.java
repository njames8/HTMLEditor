package ui;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JTextArea;

import ui.Observer;


/**
 * Tabs that are observable by observers
 * @author Jesse Roux
 */
@SuppressWarnings("serial")
public abstract class ObservableTab extends JTextArea {
	/**
	 * the list of observers watching this object
	 */
	protected List<Observer> obs;
	
	
	/**
	 * constructor
	 */
	public ObservableTab() {
		super();
		this.setTabSize(4);
		obs = new ArrayList<Observer>();
	}
	
	/**
	 * Attaches an observer to this object
	 * @param o - observer to attach
	 */
	public void attachObserver(Observer o) {
		obs.add(o);
	}

	/**
	 * detaches observer from this object
	 * @param o - observer to detach
	 */
	public void detachObserver(Observer o) {
		obs.remove(o);
	}
}
