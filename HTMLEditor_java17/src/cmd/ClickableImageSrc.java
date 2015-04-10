/**
 * 
 */
package cmd;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToolTip;

import ui.Tab;

/**
 * @author nick james
 * Listens for a click on the img tag source
 */
public class ClickableImageSrc extends MouseAdapter {
	/**
	 * the path to the image
	 */
	private String src;
	
	/**
	 * the current tab
	 */
	private Tab t;
	
	/**
	 * Tool tip over src to show instructions
	 */
	private JToolTip ttp;
	
	/**
	 * Constructor
	 * @param url - path to the image
	 * @param t - the current tab
	 */
	public ClickableImageSrc(String url, Tab t){
		super();
		this.src = url;
		this.t = t;
		this.ttp = new JToolTip();
		this.ttp.setToolTipText("Ctrl + Click to view Preview");
		
	}
	
	/**
	 * show the image preview if ctrl+left click
	 */
	@Override
	public void mouseClicked(MouseEvent me){
		if (me.getModifiers() == 18) {
			// 18 = ctrl+left click
			int x = me.getX();
			int y = me.getY();
			
			String text = t.getText();
			
			int offset = t.viewToModel(new Point(x,y));
			int searchLoc = text.indexOf(src);
			if (offset >= searchLoc && offset <= searchLoc+ src.length()) {
				ImagePreviewCMD img = new ImagePreviewCMD(src);
				img.execute();
			}
		}
	}
	
	/**
	 * if the mouse hovers over the src, show tooltip
	 */
	@Override
	public void mouseMoved(MouseEvent me){
		int x = me.getX();
		int y = me.getY();
		
		String text = t.getText();
		
		int offset = t.viewToModel(new Point(x,y));
		int searchLoc = text.indexOf(src);
		if (offset >= searchLoc && offset <= searchLoc+ src.length()) {
			System.out.println("touched link");
			ttp.setLocation(x, y);
			ttp.setVisible(true);
		} else {
			ttp.setVisible(false);
		}
	}
	
}
