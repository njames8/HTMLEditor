package parsing;
import java.util.*;

public class Parser {
	public static void main(String[] args) {
		try {
			Parse("<html><body><script rel=\"test.com\"/><p>this is text lol</p></body></html>");
		} catch (SyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static String[] allowedTags = {"html", "body", "header", "footer",
		"b", "p", "i", "li", "ol", "ul", "script", "a", "h1", "h2", "h3", "h4",
		"h5", "h6", "link"};//FIXME add rest of supported tags
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
					throw new SyntaxException(1, "Parsing failed. Expecting a closing tag of '" +
				expectedTag + "'");
				}
			}
			else {
				if (!isTagSupported(tag.split(" ")[0]) || tag.endsWith("/")) {
					continue;
				}//skip unsupported tag
				tagStack.add(tag);
			}
			System.out.println(tag);
		}
		return errs;
	}
	
	private static boolean isTagSupported(String tag) {
		for (String t : allowedTags) {
			if (t.equalsIgnoreCase(tag))
				return true;
		}
		return false;
	}
}
