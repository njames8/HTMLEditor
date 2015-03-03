package parsing;
import java.util.*;

public class Parser {
	public static List<SyntaxError> Parse(String html) throws SyntaxException {
		Stack<String> tagStack = new Stack<String>();
		ArrayList<SyntaxError> errs = new ArrayList<SyntaxError>();
		String[] parts = html.split("<");
		for (String part : parts) {
			String tag = part.split(">")[0];
			tagStack.add(tag);
			if (tag.startsWith("/")) {
				String expectedTag = tagStack.pop();
				if (!expectedTag.equals(tag)) {
					throw new SyntaxException(1, "Expecting a closing tag of " +
				expectedTag);
				}
			}
			System.out.println(tag);
		}
		return errs;
	}
}
