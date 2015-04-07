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

	// Outline View
	public void setOutlineView(boolean val) {
		outlineView = val;
		this.notifyObservers();
	}
	
	public boolean getOutlineView() {
		return outlineView;
	}
	
	// Link View
	public void setLinkView(boolean val) {
		linkView = val;
		this.notifyObservers();
	}
	
	public boolean getLinkView() {
		return linkView;
	}
	
	// Auto Indent
	public void setAutoIndent(boolean val) {
		autoIndent = val;
		this.notifyObservers();
	}
	
	public boolean getAutoIndent() {
		return autoIndent;
	}
	
	// Word Wrap
	public void setWordWrap(boolean val) {
		wordWrap = val;
		this.notifyObservers();
	}
	
	public boolean getWordWrap() {
		return wordWrap;
	}
}
