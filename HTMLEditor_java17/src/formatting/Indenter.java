package formatting;
import parsing.*;

/**
 * 
 * @author Adam Walsh
 * @author Matthew Gallagher
 * 
 * Indents the html code to be pretty
 *
 */
public class Indenter {
	
	/**
	 * Indents appropriate amount per tag
	 * @param html - html code
	 * @return - the indented html code
	 */
	public static String openIndent(String html){
		int depth = -1; //used to determine number of spaces
		String returnText = ""; //text to be returned
		String[] parts = html.split("(?=<)"); //splits current text into sections per tag
		System.out.println(parts);
		for (String part : parts){
			//finds appropriate depth and lines up number of spacing iterations to add.
			int count = 0;
			if(part.length() > 0 && part.charAt(1) == '/'){
				depth -= 1;
				count = depth;
			}
			else{
				count = depth;
				depth += 1;
			}
			String[] lines = part.split("\n");
			int countHolder = count;
			for (String line : lines) {
				if (line.equals("")) { }//skip empty line
				else {
					if (!line.startsWith("<")) {//tag content, add one more tab
						count += 1;
					}
					else if (line.endsWith("/>")) {
						depth -= 1;
					}
					line += '\n';
					while(count > 0) {
						line = "    " + line;
						count -= 1;
					}
				returnText += line;
				}
				count = countHolder;
			}
		}
		return returnText;	
	}
	
	
	/**
	 * @author Matthew Gallagher
	 * 
	 * @param htmlString
	 * 		the line where the newLine character was entered.
	 * @return
	 * 		the correct indentation for the new line.
	 */
	public static String indentNewLine(String htmlString){
		String returnText = "";
		char[] htmlChar = htmlString.toCharArray();//Splits the htmlString up so its easier to use.
		int counter = 0;
		while(htmlChar.length > counter && htmlChar[counter] == ' '){//counts how many spaces are 
																	 //in front of the first character.
			counter++;
		}
		if(htmlChar.length > counter && htmlChar[counter] == '<'){//checks to see if the first character is a tag.
			if(htmlChar.length > counter + 1 && htmlChar[counter + 1] == '/'){//checks to see if the tag is a closing tag.
				counter -= 4;//makes the counter 4 smaller so that there will be 4 less spaces on the next line
			}else if(!tagClosedOnSameLine(htmlString)){//means that the tag is an opening tag.
				counter +=4;//makes the counter 4 larger so that the next line will be indented 4 more spaces.
			}
		}
		for(int i = counter; i > 0; i--){
			returnText = returnText.concat(" ");//builds the string that will be the indentation
												//based on how many spaces are required.
		}
		return returnText;
	}
	/**
	 * @author Matthew Gallagher
	 * 
	 * @param htmlString
	 * 		
	 * @return
	 * 		true if the sent string's tags are closed
	 * 		false if the sent string's tags are not closed
	 */
	public static boolean tagClosedOnSameLine(String htmlString){
		boolean closed = false;
		try{
			Parser.Parse(htmlString);//calls Parser.parse on htmlString to see if it is syntactically correct.
									 //if htmlString is syntactically correct that mean the tag is opened and closed on the same line.
									 //if htmlString is not syntactically correct it means that the tag was left open.
			closed = true;
		}catch(SyntaxException e){
			closed = false;
		}
		return closed;
	}
}
