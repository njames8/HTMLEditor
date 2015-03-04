/**
 * 
 */
package cmd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

import ui.RightClickMenu;

/**
 * @author nick
 *
 */
public class RightClickListener extends MouseAdapter {
		private JTabbedPane tabbedPane;
		
		public RightClickListener(JTabbedPane j){
			super();
			this.tabbedPane = j;
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
	        RightClickMenu menu = new RightClickMenu(tabbedPane);
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}

