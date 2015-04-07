/**
 * 
 */
package formatting;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import settings.UserSettings;
import ui.Tab;
import ui.EditorWindow;


/**
 * @author Matthew Gallagher
 *
 */
public class HTMLTag implements Observer{
	private List<BaseTag> tags;
	
	public HTMLTag() {
		tags = new ArrayList<BaseTag>();
	}
	
	public HTMLTag(ArrayList<BaseTag> a ){
		if(a != null)
			tags = a;
		else
			tags = new ArrayList<BaseTag>();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		UserSettings u = (UserSettings)arg0;
		List<Tab> tabs = EditorWindow.getInstance().getTabs();
		for (int i = 0; i < tabs.size(); i++){
			BaseTag b = tabs.get(i).head;
			if(b != null){
				if(b.getAutoIndent() != u.getAutoIndent())
					b.setAutoIndent(u.getAutoIndent());
				if(b.getOutLineView() != u.getOutlineView())
					b.setOutLineView(u.getOutlineView());
			}
		}
	}
	
	public boolean addChild(BaseTag child, int lineNum){
		boolean added = true;
		if (tags != null){
			for (int i = 0; i < tags.size(); i++){
				added = tags.get(i).addChild(child, lineNum);
				if (added)
					break;
			}
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
		boolean added = true;
		for(int i = 0; i < tags.size(); i++){
			added = tags.get(i).addToLineNum(amount, lineNum);
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
	public String toString(){
		String text = "";
		for(int i = 0; i < tags.size(); i++){
			text += tags.get(i).toString();
		}
		return text;
	}
}
