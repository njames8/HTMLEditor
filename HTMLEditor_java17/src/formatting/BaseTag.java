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
System.out.println("start: " + s);
		if(e >= s)
			lineNumberEnd = e;
		else
			lineNumberEnd = lineNumberStart + 1;
System.out.println("end: " + e);		
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
		//TODO may need to change depending on how text tags are implemented.
		String text = "";
		for (int i = 0; i < indentLevel; i++) {
			//adds the number of indents that was sent
			text = text + "    ";
		}
		//adds the tag name
		text = text + "<" + tag + ">";
		if(collapsed == false){
			text = text + "\n";
			if(this.children.size() > 0){
				//iterates over the children of the tag.
				for(int i = 0; i < this.children.size(); i++){
					//calls this method on all the children
					text = text + this.children.get(i).getText(indentLevel + 1);
				}	
			}
			for (int i = 0; i < indentLevel; i++) {
				//adds the number of indents that was sent only if there are children.
				text = text + "    ";
			}
		}else
			text = text + "...";
		text = text + "</" + tag + ">\n";
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
		//TODO may need to change depending on how text tags are implemented.
		String text = "Not a valid Line number";
		if(lineNum >= 0){
			text = "Not this tag!!!!!!!!!!!";
			boolean t = this.inThisTag(lineNum);
System.out.println("InThisTag = " + t);
			int c = this.inChildTag(lineNum);
System.out.println("InChildTag = " + c);
			if( t && c == -1){
				text = getText(indentLevel);
			}else if(c != -1){
				text = this.children.get(c).getText(indentLevel + 1, lineNum);
			}
		}
		return text;
	}
	
	public BaseTag getChild(int lineNum){
		//TODO may need to change depending on how text tags are implemented.
System.out.println("GetChild, sent LineNum = " + lineNum);
		BaseTag child = new BaseTag();
		if(lineNum >= 0){
			boolean t = this.inThisTag(lineNum);
System.out.println("InThisTag = " + t);
			int c = this.inChildTag(lineNum);
System.out.println("InChildTag = " + c);
			if( t && c == -1){
				child = this;
			}else if(c != -1){
				child = this.children.get(c).getChild(lineNum);
			}
		}
		return child;
	}
	private int inChildTag(int lineNum){
System.out.println("\nInChildTag");
		if(this.children != null){
			for(int i = 0; i < this.children.size(); i++){
				if(this.children.get(i).getLineNumberStart() <= lineNum){
System.out.println("Sent LineNum is greaterthan or equal to the start of the child");
					if( this.children.get(i).getLineNumberEnd() >= lineNum){
System.out.println("Sent LineNum is less than or equal to the end of the child");
						return i;
					}
				}
			}
		}
		return -1;
	}
	
	private boolean inThisTag(int lineNum){
System.out.println("\nInThisTag");
		boolean b = false;
System.out.println("Sent LineNum: " + lineNum);
		if(this.getLineNumberStart() <= lineNum){ 
System.out.println("Sent LineNum is greaterthan or equal to the start");
			if(this.getLineNumberEnd() >= lineNum){
System.out.println("Sent LineNum is less than or equal to the end");
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
//System.out.println("Set <"+ tag + "> lineNumStart to " + counter);
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
		return lineNumberStart;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumberStart(int lineNumber) {
		this.lineNumberStart = lineNumber;
	}
	
	public boolean addToLineNum(int amount, int lineNum ){
		boolean added = true;
		boolean t = this.inThisTag(lineNum);
System.out.println("InThisTag = " + t);
		int c = this.inChildTag(lineNum);
System.out.println("InChildTag = " + c);
		if(t && c == -1){
			if(this.children.size() > 0){
				for (int i = 0; i < children.size(); i++){
					added = this.children.get(i).addToLineNum(amount, lineNum);
					if (added == false){
						break;
					}
				}
			}
			if(added){
				this.setLineNumberStart(this.getLineNumberStart() + amount);
				this.setLineNumberEnd(this.getLineNumberEnd() + amount);
				added = true;
			}
		}else if(c != -1){
System.out.println("called addToLineNum on a child");
			added = this.children.get(c).addToLineNum(amount, lineNum);
			if(added){
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
System.out.println("\nAdd Child");
		boolean added = false;
		// checks to see if the child has been sent to the correct tag
System.out.println("the sent line number is " + lineNum);
		if(this.getLineNumberStart() <= lineNum && lineNum <= this.getLineNumberEnd()){
System.out.println("line Number is inside the current tag");
			//check to see if there are any children already
			if(this.children.size() > 0){
System.out.println("children.size > 0");
				// iterates over all the children backwards
				for(int i = children.size() - 1; i >= 0; i--){
					// checks to see if the lineNum is greater than the start of the children.get(i)
					if(children.get(i).getLineNumberStart() <= lineNum){
						// checks to see if the lineNum is less than the start of the children.get(i)
						if(children.get(i).getLineNumberEnd() > lineNum){
							//calls this function on the child
							added = children.get(i).addChild(child, lineNum);
						}else{
							children.add(i + 1, child);
							added = true;
						}
						break;
					}
				}
				if(added == false){
					children.add(0,child);
				}
			}else{
System.out.println("called addChild(BaseTag)");
				added = this.addChild(child);
			}
		}
		return added;	
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
		
		return text;
	}
}
