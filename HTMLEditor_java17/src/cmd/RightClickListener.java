/**
 * 
 */
package cmd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.RightClickMenu;

/**
 * @author nick
 *
 */
public class RightClickListener extends MouseAdapter {
		public RightClickListener(){
			super();
		}
		
	    public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            rightClick(e);
	    }

	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            rightClick(e);
	    }

	    private void rightClick(MouseEvent e){
	        RightClickMenu menu = new RightClickMenu();
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}

