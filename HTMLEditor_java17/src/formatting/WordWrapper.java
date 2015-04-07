package formatting;

import java.awt.FontMetrics;
import java.util.HashMap;

import javax.swing.JTextArea;

/**
 * 
 * @author Jesse
 *
 */
@SuppressWarnings("unused")
public class WordWrapper {
	/**
	 * Formats raw text into properly word wrapped text for displaying to the user
	 * 
	 * @param text The raw text to be wrapped split by lines
	 * @param textarea The textarea that will be wrapping the text
	 * @return A word wrapped version of the raw text
	 */
	public static String wrapText(String[] text, JTextArea textarea) {
		String out = "";
		
		// Get the font metrics so we know when to wrap the lines
		FontMetrics fm = textarea.getFontMetrics(textarea.getFont());
		int[] charWidths = fm.getWidths();
		int width = textarea.getWidth(); // FIXME: this probably doesn't return the inner width
		
		for(String line : text) {
			// Not sure if this is faster or not, but if the line doesn't need to be wrapped 
			// then we just add it as is.
			if(fm.stringWidth(line) < width) {
				out += line + '\n';
				continue;
			}
			int lineWidth = 0;
			
			// See how much indentation this line has
			String indentation = "";
			int indentationWidth = 0;
			
			boolean hitContent = false;
			
			String lineBuilder = "";
			
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				
				// Count how much whitespace the line starts with
				if(!hitContent) {
					// Check to see if we've hit a space or a tab. If we haven't, we know 
					// we've hit the actual content of the line
					if(c == ' ')
					{
						indentation += ' ';
						indentationWidth += charWidths[' '];
					}
					else if(c == '\t')
					{
						// FIXME: get tab size from model, magic number alert, police help
						for(int j = 0; i < 4 /* Tab size */; i++)
							indentation += ' ';
						
						indentationWidth += charWidths[' '] * 4;
					}
					else
					{
						hitContent = true;
						lineBuilder = indentation;
						
						// FIXME: this will break if the indentation is greater than the window width
						lineWidth = indentationWidth;
					}
				} else {
					// Will appending this character go beyond our textarea width?
					if(lineWidth + charWidths[c] > width) {
						// dump the current line 
						out += lineBuilder + '\n';
						lineBuilder = indentation;
					}
					
					lineBuilder += c;
				}
			}
		}
		
		// There will always be an extra newline at the end since we don't look ahead 
		if(out.endsWith("\n"))
			out = out.substring(0, out.length() - 1);
		
		return out;
	}
}
