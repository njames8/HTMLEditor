package Formatting;
import java.util.*;


public class Indenter {
	//TODO
	//just for testing will be removed
	public static void main(String[] args){
		String inPut = "<Head>";
		String returnText = Indenter.indentNewLine(inPut);
		System.out.println(inPut);
		System.out.println(returnText);
		inPut = "    <Head>";
		returnText = Indenter.indentNewLine(inPut);
		System.out.println(inPut);
		System.out.println(returnText);
		inPut = "        <Head>";
		returnText = Indenter.indentNewLine(inPut);
		System.out.println(inPut);
		System.out.println(returnText);
		inPut = "        </Head>";
		returnText = Indenter.indentNewLine(inPut);
		System.out.println(inPut);
		System.out.println(returnText);
		inPut = "    </Head>";
		returnText = Indenter.indentNewLine(inPut);
		System.out.println(inPut);
		System.out.println(returnText);
		inPut = "</Head>";
		returnText = Indenter.indentNewLine(inPut);
		System.out.println(inPut);
		System.out.println(returnText);
	}
	public static String openIndent(String html){
		int depth = 0; //used to determine number of spaces
		String returnText = ""; //text to be returned
		String[] parts = html.split("(?=<)"); //splits current text into sections per tag
		for (String part : parts){
			//finds appropriate depth and lines up number of spacing iterations to add.
			int count = 0;
			if(part.length()> 0 && part.charAt(1) == '/'){
				depth -= 1;
				count = depth;
			}
			else{
				count = depth;
				depth += 1;
			}
			while(count > 0){
				part = "    " + part;
				count -= 1;
			}
			returnText = returnText + part;
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
			}else{//means that the tag is an opening tag.
				//TODO need to check to see if the tag is closed on the same line.
				counter +=4;//makes the counter 4 larger so that the next line will be indented 4 more spaces.
			}
		}
		for(int i = counter; i > 0; i--){
			returnText = returnText.concat(" ");//builds the string that will be the indentation
												//based on how many spaces are required.
		}
		returnText = returnText.concat("" + counter);// Just for testing
		return returnText;
	}

}
