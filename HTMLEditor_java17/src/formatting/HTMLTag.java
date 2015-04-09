/**
 * 
 */
package formatting;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew Gallagher
 *
 */
public class HTMLTag{
	private List<BaseTag> tags;
	private boolean outLineView = false;
	private boolean autoIndent = true;
	
	public HTMLTag() {
		tags = new ArrayList<BaseTag>();
	}
	
	public HTMLTag(ArrayList<BaseTag> a ){
		if(a != null)
			tags = a;
		else
			tags = new ArrayList<BaseTag>();
	}
	
	public boolean addChild(BaseTag child, int lineNum){
		boolean added = false;
		child.setAutoIndent(autoIndent);
		child.setOutLineView(outLineView);
		if (tags != null){
			for (int i = 0; i < tags.size(); i++){
				boolean b = tags.get(i).inThisTag(lineNum);
				if(b){
					added = tags.get(i).addChild(child, lineNum);
					break;
				}
			}
			if(!added)
				tags.add(child);
		}else{
			tags = new ArrayList<BaseTag>();
			tags.add(child);
		}
		return added;
	}
	/**
	 *
	 * @param indentLevel
	 * if tag is collapsed
	 * 		@return the tag opener and closer on the same line correctly indented.
	 * if tag is not collapsed
	 * 		@return the tag opener with all its children then the tag closer all on different lines correctly indented.
	 */
	public String getText(int indentLevel){
		String text = "";
		for(int i = 0; i < tags.size(); i++){
			text += tags.get(i).getText(indentLevel);
		}
		return text;
	}
	
	/**
	 *
	 * @param indentLevel
	 * if tag is collapsed
	 * 		@return the tag opener and closer on the same line correctly indented.
	 * if tag is not collapsed
	 * 		@return the tag opener with all its children then the tag closer all on different lines correctly indented.
	 */
	public String getText(int indentLevel, int lineNum){
		String text = "";
		if(lineNum >= 0){
			for (int i = 0; i < tags.size(); i++){
				text += tags.get(i).getText(indentLevel, lineNum);
			}
		}
		return text;
	}
	public int traverseForLineNumbers (){
		int counter = 0;
		if(this.tags != null && this.tags.size() > 0){
			//iterates over all the children of the tag
			for(int i = 0; i < tags.size(); i++){
				//calls this method on each of its children
				counter = tags.get(i).traverseForLineNumbers(counter);
			}
		}
		return counter;
	}
	
	public int getIndentLevel(int lineNum){
		int indent = 0;
		for(int i = 0; i < tags.size(); i++){
			indent = tags.get(i).getIndentLevel(lineNum);
			if(indent != 0)
				break;
		}
		return indent;
	}
	public boolean addToLineNum(int amount, int lineNum ){
		boolean added = false;
		for(int i = 0; i < tags.size(); i++){
			boolean b = tags.get(i).inThisTag(lineNum);
			if(b)
				added = tags.get(i).addToLineNum(amount, lineNum);
			else if(!b && added)
				tags.get(i).addToLineNum(amount);
		}
		return added;
	}
	public BaseTag getChild(int lineNum){
		BaseTag child = null;
		for(int i = 0; i < tags.size(); i++){
			child = tags.get(i).getChild(lineNum);
			if(child != null)
				break;
		}
		return child;
	}
	public void setOutLineView(boolean b){
		outLineView = b;
		for(int i = 0; i < tags.size(); i++){
			tags.get(i).setOutLineView(outLineView);
		}
	}
	public void setAutoIndent(boolean b){
		autoIndent = b;
		for(int i = 0; i < tags.size(); i++){
			tags.get(i).setAutoIndent(autoIndent);
		}
	}
	public String toString(){
		String text = "";
		for(int i = 0; i < tags.size(); i++){
			text += tags.get(i).toString();
		}
		return text;
	}
}
