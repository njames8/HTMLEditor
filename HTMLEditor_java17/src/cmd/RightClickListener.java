/**
 * 
 */
package cmd;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.EditorWindow;
import ui.RightClickMenu;

/**
 * @author Nick James
 * Listens for a Right Click from the User
 * and shows the right click menu
 */
public class RightClickListener extends MouseAdapter {
		/**
		 * the main EditorWindow
		 */
		private EditorWindow ew;
		/**
		 * Constructor
		 */
		public RightClickListener(EditorWindow e){
			super();
			ew = e;
		}
		
		/**
		 * Called when the mouse is pressed
		 * Triggers the right click menu
		 */
	    public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            rightClick(e);
	    }
	    
	    /**
	     * Called when the mouse is pressed
		 * Triggers the right click menu
	     */
	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            rightClick(e);
	    }

	    /**
	     * Creates a new RightClickMenu and shows it at
	     * the cursor
	     * @param e
	     */
	    private void rightClick(MouseEvent e){
	        RightClickMenu menu = new RightClickMenu(ew);
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}

