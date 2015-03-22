/**
 * 
 */
package formatting;
import java.util.*;

/**
 * @author adam walsh
 *
 */
public class BaseTag {
	private String tag;//tag value e.g. 'p' for a <p> tag
	private boolean collapsed = false;
	private ArrayList<BaseTag> children;
	private int lineNumberStart, lineNumberEnd;
	public BaseTag() {
		this.children = new ArrayList<BaseTag>();
	}
	public BaseTag(String t, int s, int e, boolean c, ArrayList<BaseTag> a){
		tag = t;
		collapsed = c;
		
		if(s >= 0)
			lineNumberStart = s;
//System.out.println("start: " + s);
		if(e >= s)
			lineNumberEnd = e;
		else
			lineNumberEnd = lineNumberStart + 1;
//System.out.println("end: " + e);		
		if(a != null)
			children = a;
		else
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
		String text = "";
		String t = "";
		//adds the number of indents that was sent
		for(int i = 0; i < indentLevel; i++){
			t += "    ";
		}
		//adds the tag name
		text += t + "<" + tag + ">";
		if(!collapsed){
			text += "\n";
			if(this.children.size() > 0){
				//iterates over the children of the tag.
				for(int i = 0; i < this.children.size(); i++){
					//calls this method on all the children
					text += this.children.get(i).getText(indentLevel + 1);
				}
			}
			text += t;
		}else
			text += "...";
		text += "</" + tag + ">";
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
			if(this.inThisTag(lineNum)){
				int c = this.inChildTag(lineNum);
				if( c >= 0){
					text = this.children.get(c).getText(indentLevel + 1, lineNum);
				}else if( c == -1){
					text = getText(indentLevel);
				}
			}
		}
		return text;
		
		
/*		
		//TODO may need to change depending on how text tags are implemented.
		String text = "Not a valid Line number";
		if(lineNum >= 0){
			text = "Not this tag!!!!!!!!!!!";
			boolean t = this.inThisTag(lineNum);
//System.out.println("InThisTag = " + t);
			int c = this.inChildTag(lineNum);
//System.out.println("InChildTag = " + c);
			if( t && c == -1){
				text = getText(indentLevel);
			}else if(c != -1){
				text = this.children.get(c).getText(indentLevel + 1, lineNum);
			}
		}
		return text;
*/
	}
	
	public BaseTag getChild(int lineNum){
		//TODO may need to change depending on how text tags are implemented.
//System.out.println("GetChild, sent LineNum = " + lineNum);
		BaseTag child = new BaseTag();
		if(lineNum >= 0){
			boolean t = this.inThisTag(lineNum);
//System.out.println("InThisTag = " + t);
			int c = this.inChildTag(lineNum);
//System.out.println("InChildTag = " + c);
			if( t && c == -1){
				child = this;
			}else if(c != -1){
				child = this.children.get(c).getChild(lineNum);
			}
		}
		return child;
	}
	
	/**
	 * 
	 * @param lineNum
	 * @return child index OR
	 * 		-2 if the number of children is greater than 0 but the line number is not in any of the children. 
	 * 		-1 if there are no children.
	 */
	private int inChildTag(int lineNum){
//System.out.println("\nInChildTag");
		if(this.children.size() > 0){
			for(int i = 0; i < this.children.size(); i++){
				if(this.children.get(i).getLineNumberStart() <= lineNum){
//System.out.println("Sent LineNum is greaterthan or equal to the start of the child");
					if( this.children.get(i).getLineNumberEnd() >= lineNum){
//System.out.println("Sent LineNum is less than or equal to the end of the child");
						return i;
					}
				}
			}
			return -2;
		}
		return -1;
	}
	
	private boolean inThisTag(int lineNum){
//System.out.println("\nInThisTag");
		boolean b = false;
//System.out.println("Sent LineNum: " + lineNum);
		if(this.getLineNumberStart() <= lineNum){ 
//System.out.println("Sent LineNum is greaterthan or equal to the start");
			if(this.getLineNumberEnd() >= lineNum){
//System.out.println("Sent LineNum is less than or equal to the end");
				b = true;
			}
		}
		return b;
	}
	/**
	 * @param counter
	 * @return the lineNumber of the next line.
	 */
	public int traverseForLineNumbers (int counter){
//System.out.println("traverse For Line Numbers");
		// sets the line number of this tag to be the counter
		this.setLineNumberStart(counter);
System.out.println("Set <"+ tag + "> lineNumStart to " + counter);
		counter = counter + 1;
		if(this.children.size() > 0){
//System.out.println("number of direct Children is " + this.children.size());
			//iterates over all the children of the tag
			for(int i = 0; i < children.size(); i++){
				//calls this method on each of its children
				counter = children.get(i).traverseForLineNumbers(counter);
			}
		}
		lineNumberEnd = counter;
//System.out.println("Set </" + tag + "> lineNumEnd to " + counter);
		return counter + 1;
	}

	/**
	 * @return the lineNumber
	 */
	public int getLineNumberStart() {
System.out.println("line number start sent");
		return lineNumberStart;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumberStart(int lineNumber) {
System.out.println("line number end changed");
		this.lineNumberStart = lineNumber;
	}
	
	public boolean addToLineNum(int amount, int lineNum ){
		boolean added = true;
		boolean t = this.inThisTag(lineNum);
System.out.println("InThisTag = " + t);
		int c = this.inChildTag(lineNum);
System.out.println("InChildTag = " + c);
		if(t && (c == -1 || c == -2)){
			if(c == -2){
				for (int i = 0; i < children.size(); i++){
					added = this.children.get(i).addToLineNum(amount, lineNum);
					if (added == false){
						break;
					}
				}
			}
			if(added){
				if(c == -2){
System.out.println("added to the start and end of " + tag);
					this.setLineNumberStart(this.getLineNumberStart() + amount);
					this.setLineNumberEnd(this.getLineNumberEnd() + amount);
				}else if(c == -1){
System.out.println("added to the end of " + tag);
					this.setLineNumberEnd(this.getLineNumberEnd() + amount);
				}
			}
		}else if(c != -1){
System.out.println("called addToLineNum on a child");
			added = this.children.get(c).addToLineNum(amount, lineNum);
			if(added){
System.out.println("added to the end");
				this.setLineNumberEnd(this.getLineNumberEnd() + amount);
			}
		}
		return added;
	}
	
	/**
	 * 
	 * @param child
	 * 
	 * Adds the sent child to the end of this baseTag's children
	 */
	public boolean addChild(BaseTag child){
//System.out.println("Added new child to end of list");
		children.add(child);
		return true;
	}
	/**
	 * 
	 * @param child
	 * @param lineNum
	 * 
	 * Adds the sent child to the list of children and puts it in the
	 * spot that is specified in the lineNum that was sent.
	 */
	public boolean addChild(BaseTag child, int lineNum){
		boolean added = false;
		// checks to see if the child has been sent to the correct tag
		if(this.inThisTag(lineNum)){
			//check to see if there are any children already
			if(this.children.size() > 0){
				// iterates over all the children backwards
				for(int i = 0; i < this.children.size(); i++){
					// checks to see if the lineNum is greater than the start of the children.get(i)
					if(this.children.get(i).getLineNumberStart() <= lineNum){
						// checks to see if the lineNum is less than the start of the children.get(i)
						if(this.children.get(i).getLineNumberEnd() >= lineNum){
							//calls this function on the child
							added = children.get(i).addChild(child, lineNum);
							break;
						}
					}
				}
				if(!added){
					this.children.add(0, child);
				}
			}else{
				this.addChild(child);
			}
		}
		return added;
/*		
//System.out.println("\nAdd Child");
		boolean added = false;
		
//System.out.println("the sent line number is " + lineNum);
		if(this.getLineNumberStart() <= lineNum && lineNum <= this.getLineNumberEnd()){
//System.out.println("line Number is inside the current tag");
			
			if(this.children.size() > 0){
//System.out.println("children.size > 0");
				
				for(int i = children.size() - 1; i >= 0; i--){
					
					if(children.get(i).getLineNumberStart() <= lineNum){
						
						if(children.get(i).getLineNumberEnd() > lineNum){
							
							added = children.get(i).addChild(child, lineNum);
						}
						break;
					}
				}
				if(added == false){
					children.add(0,child);
				}
			}else{
//System.out.println("called addChild(BaseTag)");
				added = this.addChild(child);
			}
		}
		return added;
*/
	}

	/**
	 * @return the lineNumberEnd
	 */
	public int getLineNumberEnd() {
		return lineNumberEnd;
	}

	/**
	 * @param lineNumberEnd the lineNumberEnd to set
	 */
	public void setLineNumberEnd(int lineNumberEnd) {
		this.lineNumberEnd = lineNumberEnd;
	}
	
	public String toString(){
		String text = "";
		//TODO may need to change depending on how text tags are implemented.
		//adds the tag name
		text = text + this.getLineNumberStart() + "    <" + tag + ">";
		if(collapsed == false){
			text = text + "\n";
			if(this.children.size() > 0){
				//iterates over the children of the tag.
				for(int i = 0; i < this.children.size(); i++){
				//calls this method on all the children
					text = text + this.children.get(i).toString();
				}	
			}
		}else
			text = text + "...";
		text = text + this.getLineNumberEnd() + "    </" + tag + ">\n";
		return text;
	}
}
