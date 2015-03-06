package parsing;

/**
 * Represents an exception in HTML code
 * @author Adam Walsh
 *
 */
@SuppressWarnings("serial")
public class SyntaxException extends Exception {
	
	/**
	 * constructor
	 * @param lineNum - line number of exception
	 * @param msg - message to display
	 */
	public SyntaxException(int lineNum, String msg) {
		super("Syntax exception @ " + lineNum 
				+ " " + msg);
	}
}
