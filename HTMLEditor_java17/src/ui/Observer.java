package ui;

/**
 * Interface for the Editor window to implement
 * @author Jesse Roux
 *
 */
public interface Observer {
	
	/**
	 * updates a tab t
	 * @param t - the current tab
	 */
	public void update(Tab t);
}
