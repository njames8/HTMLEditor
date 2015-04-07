/**
 * 
 */
package formatting;
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
	
	public HTMLTag() {
		
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
}
