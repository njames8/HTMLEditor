package parsing;
/**
 * Represents a syntax error in the HTML code
 * @author Adam Walsh
 *
 */
public class SyntaxError {
	
	/**
	 * line number of the error
	 */
	private int lineNum;
	
	/**
	 * The message displayed
	 */
	private String msg;
	
	/**
	 * Constructor
	 * @param lineNum - line number of error
	 * @param msg - the message to display
	 */
	public SyntaxError(int lineNum, String msg) {
		this.lineNum = lineNum;
		this.msg = msg;
	}
	
	/**
	 * makes the error readable to humans
	 */
	public String toString() {
		return "Error @ " + this.lineNum +
				" - " + this.msg;
	}
}
