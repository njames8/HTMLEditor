package parsing;
import java.util.*;

public class Parser {
	private static String[] allowedTags = {"html", "body", "head", "footer",
		"b", "p", "i", "li", "ol", "ul", "script", "a", "h1", "h2", "h3", "h4",
		"h5", "h6", "link", "meta", "title", "link", "nav", "div", "button",
		"header", "video", "source", "img", "section", "br", "td", "input", "iframe",
		"param", "dt", "dd", "thead", "tr", "td", "tfoot", "colgroup", "strong",
		"small"};//FIXME add rest of supported tags
	private static String[] optionalCloseTags = { "source", "html", "head",
		"body", "p", "dt", "dd", "li", "option", "thead", "tr", "td", "tfoot",
		"colgroup"};
	private static String[] forbiddenCloseTags = { "meta", "img", "input",
		"br", "frame", "param", "link"};
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
			if (part.startsWith("!"))//skip comments
				continue;
			String tag = part.split(">")[0];
			if (tag.startsWith("/")) {//ending tag
				if (isTagForbidden(tag.substring(0)))
					throw new SyntaxException(1, "Parsing failed, forbidden closing tag '" +
				tag + "' found.");
				String expectedTag = tagStack.pop();
				tag = tag.substring(1);//cut off end tag slash
				if (!expectedTag.equals(tag)) {//doesn't match last opened tag
					if (isTagOptionalClose(expectedTag) || isTagForbidden(expectedTag))//it's okay, it's optional
						continue;
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
				if (!isTagForbidden(tagName))//not forbidden
					tagStack.add(tagName);
			}
		}
		if (!tagStack.empty())
			throw new SyntaxException(1, "Parsing failed. Tag '" + tagStack.pop() + "'" +
		" not closed.");
	}
	
	private static boolean isTagForbidden(String tag) {
		for (String t : forbiddenCloseTags)
			if (t.equals(tag))
				return true;
		return false;
	}

	private static boolean isTagSupported(String tag) {
		for (String t : allowedTags) {
			if (t.equalsIgnoreCase(tag))
				return true;
		}
		return false;
	}
	
	private static boolean isTagOptionalClose(String tag) {
		for (String t : optionalCloseTags)
			if (t.equalsIgnoreCase(tag))
				return true;
		return false;
	}
}
