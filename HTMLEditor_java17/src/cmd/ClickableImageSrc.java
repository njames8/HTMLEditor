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
 *
 */
public class ClickableImageSrc extends MouseAdapter {
	private String url;
	private Tab t;
	private JToolTip ttp;
	
	public ClickableImageSrc(String url, Tab t){
		super();
		this.url = url;
		this.t = t;
		this.ttp = new JToolTip();
		this.ttp.setToolTipText("Ctrl + Click to view Preview");
		
	}
	
	@Override
	public void mouseClicked(MouseEvent me){
		if (me.getModifiers() == 18) {
			// 18 = ctrl+left click
			int x = me.getX();
			int y = me.getY();
			
			String text = t.getText();
			
			int offset = t.viewToModel(new Point(x,y));
			int searchLoc = text.indexOf(url);
			if (offset >= searchLoc && offset <= searchLoc+ url.length()) {
				ImagePreviewCMD img = new ImagePreviewCMD(url);
				img.execute();
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent me){
		int x = me.getX();
		int y = me.getY();
		
		String text = t.getText();
		
		int offset = t.viewToModel(new Point(x,y));
		int searchLoc = text.indexOf(url);
		if (offset >= searchLoc && offset <= searchLoc+ url.length()) {
			System.out.println("touched link");
			ttp.setLocation(x, y);
			ttp.setVisible(true);
		} else {
			ttp.setVisible(false);
		}
	}
	
}
