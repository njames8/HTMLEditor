package parsing;

public class SyntaxError {
	private int lineNum;
	private String msg;
	public SyntaxError(int lineNum, String msg) {
		this.lineNum = lineNum;
		this.msg = msg;
	}
	
	public String toString() {
		return "Error @ " + this.lineNum +
				" - " + this.msg;
	}
}
