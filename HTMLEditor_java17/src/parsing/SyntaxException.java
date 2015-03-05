package parsing;

@SuppressWarnings("serial")
public class SyntaxException extends Exception {
	public SyntaxException(int lineNum, String msg) {
		super("Syntax exception @ " + lineNum 
				+ " " + msg);
	}
}
