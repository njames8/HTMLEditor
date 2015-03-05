package ui;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JTextArea;

import ui.Observer;

@SuppressWarnings("serial")
public abstract class ObservableTab extends JTextArea {
	protected List<Observer> obs;
	
	public ObservableTab() {
		super();
		obs = new ArrayList<Observer>();
	}
	
	public void attachObserver(Observer o) {
		obs.add(o);
	}
	
	public void detachObserver(Observer o) {
		obs.remove(o);
	}
}
