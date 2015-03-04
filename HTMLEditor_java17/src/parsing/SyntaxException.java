package parsing;

public class SyntaxException extends Exception {
	public SyntaxException(int lineNum, String msg) {
		super("Syntax exception @ " + lineNum 
				+ " " + msg);
	}
}
