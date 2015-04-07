import java.util.Observable;

/**
 * 
 * @author Jesse Roux
 *
 */
public class UserSettings extends Observable {
	private boolean outlineView, linkView, autoIndent, wordWrap;
		
	public UserSettings() {
		resetSettings();
	}
	
	/**
	 * Resets/inits the settings. Can be used to reset the program
	 * settings to their defaults 
	 */
	public void resetSettings() {
		outlineView = false;
		linkView = false;
		autoIndent = true;
		wordWrap = true;
	}
	
	/**
	 * Sets the outline view
	 * 
	 * @param val enable or disables outline view
	 */
	public void setOutlineView(boolean val) {
		outlineView = val;
		this.notifyObservers();
	}
	
	/**
	 * Gets the outline view.
	 * 
	 * @return whether outline view is enabled
	 */
	public boolean getOutlineView() {
		return outlineView;
	}
	
	public void setLinkView(boolean val) {
		outlineView = val;
		this.notifyObservers();
	}
	
	public boolean getLinkView() {
		return outlineView;
	}
	
	public void setAutoIndent(boolean val) {
		outlineView = val;
		this.notifyObservers();
	}
	
	public boolean getAutoIndent() {
		return outlineView;
	}
	
	public void setWordWrap(boolean val) {
		outlineView = val;
		this.notifyObservers();
	}
	
	public boolean getWordWrap() {
		return outlineView;
	}
}
