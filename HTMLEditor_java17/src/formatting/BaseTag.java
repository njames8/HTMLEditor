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
	 * @param counter
	 * @return
	 */
	public int traverse (int counter){
		this.setLineNumber(counter);
		if(this.children.size() > 0){
			counter = counter + 1;
			for(int i = 0; i < children.size(); i++){
				counter = children.get(i).traverse(counter);
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
}
