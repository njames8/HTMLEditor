package Formatting;
import java.util.*;


public class Indenter {
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

}
