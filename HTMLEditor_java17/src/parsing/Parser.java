package parsing;
import java.util.*;

import ui.Tab;
/*
 * Parser
 * 
 * Observers the tab and parses the contained text
 * 
 * @author Adam Walsh
 * 
 */
public class Parser implements ui.Observer {
	/**
	 * The allowed tags to parse
	 */
	private String[] allowedTags = {"html", "body", "head", "footer",
		"b", "p", "i", "li", "ol", "ul", "script", "a", "h1", "h2", "h3", "h4",
		"h5", "h6", "link", "meta", "title", "link", "nav", "div", "button",
		"header", "video", "source", "img", "section", "br", "td", "input", "iframe",
		"param", "dt", "dd", "thead", "tr", "td", "tfoot", "colgroup", "strong",
		"small"};//FIXME add rest of supported tags
	
	/**
	 * The closing tags to parse
	 */
	private String[] optionalCloseTags = { "source", "html", "head",
		"body", "p", "dt", "dd", "li", "option", "thead", "tr", "td", "tfoot",
		"colgroup"};
	
	/**
	 * Look over these tags
	 */
	private String[] forbiddenCloseTags = { "meta", "img", "input",
		"br", "frame", "param", "link"};
	/*
	 * List to store the found errors in
	 * 
	 * @author Adam Walsh
	 */
	private ArrayList<SyntaxException> errors;
	/*
	 * Current character(s) being built up by the state machine (this)
	 */
	private String current;
	/*
	 * Current state of the parser
	 */
	private ParserState state;
	/*
	 * Constructor
	 * 
	 * @author Adam Walsh
	 */
	public Parser() {
		//TODO start at empty state
	}
	/*
	 * Get the current errors list
	 * 
	 * @author Adam Walsh
	 * 
	 */
	public ArrayList<SyntaxException> getErrors() {
		return errors;
	}
	/*
	 * Check for an element in an array of that type of elements
	 * 
	 * @author Adam Walsh
	 * 
	 */
	private <E> boolean isIn (E elem, E[] col) {
		for(E each : col)
			if (each.equals(elem))
				return true;
		return false;
	}
	
	/**
	 * Validates the HTML code
	 * @param html - the HTML code
	 * @throws SyntaxException
	 */
	public void Parse(String html) {
		errors = new ArrayList<SyntaxException>();
		Stack<String> tagStack = new Stack<String>();
		String[] parts = html.split("<");
		int tagCount = parts.length;
		if (tagCount == 1)
			errors.add(new SyntaxException(1, "Parsing failed, no tags found."));
		else 
			if (tagCount == 2)
				errors.add(new SyntaxException(1, "Parsing failed, only one tag found."));
		for (String part : parts) {
			if (part.startsWith("!"))//skip comments
				continue;
			String tag = part.split(">")[0];
			if (tag.startsWith("/")) {//ending tag
				if (isIn(tag.substring(0), forbiddenCloseTags))
					errors.add(new SyntaxException(1, "Parsing failed, forbidden closing tag '" +
				tag + "' found."));
				String expectedTag = tagStack.pop();
				tag = tag.substring(1);//cut off end tag slash
				if (!expectedTag.equals(tag)) {//doesn't match last opened tag
					if (isIn(expectedTag, optionalCloseTags) || isIn(expectedTag, forbiddenCloseTags))//it's okay, it's optional
						continue;
					errors.add(new SyntaxException(1, "Parsing failed. Expecting a closing tag of '" +
				expectedTag + "'"));
				}
			}
			else {//non-ending tag
				if (tag.equals("")) { continue; }
				String tagName = tag.split(" ")[0];
				if (!isIn(tagName, allowedTags) || tagName.endsWith("/")) {
					errors.add(new SyntaxException(1, "Parsing failed. Invalid tag found, '" +
				tagName + "'"));
				}
				if (!isIn(tagName, forbiddenCloseTags))//not forbidden
					tagStack.add(tagName);
			}
		}
		if (!tagStack.empty())
			errors.add(new SyntaxException(1, "Parsing failed. Tag '" + tagStack.pop() + "'" +
		" not closed."));
	}

	@Override
	public void update(Tab t) {
		Parse(t.getText());
	}

	private interface ParserState {
		public void takeInput(char nxt);
	}

	private class EmptyState implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class OutOfTag implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class TagBuilding implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class TagName implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class AltComment implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class AltCommentClosing implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class ClosingTag implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class ClosingTagName implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class Content implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class AttrBuilding implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class AttrKey implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class AttrValue implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class Doctype implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class CommentStart implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class InComment implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class CommentCloseStart implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}

	private class CommentReadyToClose implements ParserState {
		@Override
		public void takeInput(char nxt) {
			// TODO Auto-generated method stub
		}
	}
}
