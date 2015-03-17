/**
 * 
 */
package formatting;
import java.util.*;

/**
 * @author adamwalsh
 *
 */
public class BaseTag {
	private String tag;//tag value e.g. 'p' for a <p> tag
	private Boolean collapsed;
	private ArrayList<BaseTag> children;
	private int lineNumber;
	public BaseTag() {
		this.children = new ArrayList<BaseTag>();
	}
	
	public void ToggleCollapse() {
		this.collapsed = !this.collapsed;
	}
	
	public String GetContent() {
		return "";//TODO
	}
	
	/**
	 * @author Matthew Gallagher
	 * 
	 */
	
	/**
	 * 
	 * @param indentLevel
	 * if tag is collapsed
	 * 		@return the tag opener and closer on the same line correctly indented.
	 * if tag is not collapsed
	 * 		@return the tag opener with all its children then the tag closer all on different lines correctly indented.
	 */
	public String getText(int indentLevel){
		//TODO may need to change depending on how text tags are implemented.
		String text = "";
		for (int i = 0; i <= indentLevel; i++) {
			text = text + "    ";//adds the number of indents that was sent
		}
		text = "<" + tag + ">";//adds the tag name
		if(this.children.size() > 0 && collapsed == false){
			text = text + "\n";
			for(int i = 0; i < this.children.size(); i++){//iterates over the children of the tag.
				text = text + this.children.get(i).getText(indentLevel + 1);//calls this method on all the children
			}
			for (int i = 0; i <= indentLevel; i++) {
				text = text + "    ";//adds the number of indents that was sent only if there are children.
			}
		}
		text = text + "</" + tag + ">\n";
		return text;
	}
	/**
	 * @param counter
	 * @return the lineNumber of the next line.
	 */
	public int traverseForLineNumbers (int counter){
		this.setLineNumber(counter);// sets the line number of this tag to be the counter
		if(this.children.size() > 0){
			counter = counter + 1;
			for(int i = 0; i < children.size(); i++){//iterates over all the children of the tag
				counter = children.get(i).traverseForLineNumbers(counter);//calls this method on each of its children
			}
		}
		return counter + 1;
	}

	/**
	 * @return the lineNumber
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	/**
	 * 
	 * @param child
	 * 
	 * Adds the sent child to the end of this baseTag's children
	 */
	public void addChild(BaseTag child){
		children.add(child);
	}
	/**
	 * 
	 * @param child
	 * @param lineNum
	 * 
	 * Adds the sent child to the list of children and puts it in the
	 * spot that is specified in the lineNum that was sent.
	 * 
	 * @throws Exception 
	 * 		throws exception if the sent line number is smaller than this lineNumber
	 */
	public void addChild(BaseTag child, int lineNum) throws Exception{
		if(lineNum < this.getLineNumber()){
			if(this.children.size() > 0){
				for(int i = children.size() - 1; i >= 0; i++){
					if(children.get(i).getLineNumber() < lineNum){
						children.add(i,child);
						break;
					}
				}
			}
		}else{
			throw new Exception();
		}
		
	}
}
