package parsing;
import java.util.*;

public class Parser {
	public static List<SyntaxError> Parse(String html) throws SyntaxException {
		Stack<String> tagStack = new Stack<String>();
		ArrayList<SyntaxError> errs = new ArrayList<SyntaxError>();
		String[] parts = html.split("<");
		for (String part : parts) {
			String tag = part.split(">")[0];
			if (tag.startsWith("/")) {
				String expectedTag = tagStack.pop();
				tag = tag.substring(1);//cut off end tag slash
				if (!expectedTag.equals(tag)) {
					throw new SyntaxException(1, "Parsing failed. Expecting a closing tag of " +
				expectedTag);
				}
			}
			else {
				tagStack.add(tag);
			}
			System.out.println(tag);
		}
		return errs;
	}
}
