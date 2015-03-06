package parsing;
import java.util.*;

public class Parser {
	private static String[] allowedTags = {"html", "body", "header", "footer",
		"b", "p", "i", "li", "ol", "ul", "script", "a", "h1", "h2", "h3", "h4",
		"h5", "h6", "link"};//FIXME add rest of supported tags
	public static void Parse(String html) throws SyntaxException {
		Stack<String> tagStack = new Stack<String>();
		String[] parts = html.split("<");
		int tagCount = parts.length;
		if (tagCount == 1)
			throw new SyntaxException(1, "Parsing failed, no tags found.");
		else 
			if (tagCount == 2)
				throw new SyntaxException(1, "Parsing failed, only one tag found.");
		for (String part : parts) {
			String tag = part.split(">")[0];
			if (tag.startsWith("/")) {//ending tag
				String expectedTag = tagStack.pop();
				tag = tag.substring(1);//cut off end tag slash
				if (!expectedTag.equals(tag)) {//matches last opened tag
					throw new SyntaxException(1, "Parsing failed. Expecting a closing tag of '" +
				expectedTag + "'");
				}
			}
			else {//non-ending tag
				if (tag.equals("")) { continue; }
				String tagName = tag.split(" ")[0];
				if (!isTagSupported(tagName) || tagName.endsWith("/")) {
					throw new SyntaxException(1, "Parsing failed. Invalid tag found, '" +
				tagName + "'");
				}
				tagStack.add(tagName);
			}
			System.out.println(tag);
		}
	}
	
	private static boolean isTagSupported(String tag) {
		for (String t : allowedTags) {
			if (t.equalsIgnoreCase(tag))
				return true;
		}
		return false;
	}
}
