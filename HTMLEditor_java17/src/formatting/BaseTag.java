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
	public BaseTag() {
		this.children = new ArrayList<BaseTag>();
	}
	
	public void ToggleCollapse() {
		this.collapsed = !this.collapsed;
	}
	
	public String GetContent() {
		
	}
}
