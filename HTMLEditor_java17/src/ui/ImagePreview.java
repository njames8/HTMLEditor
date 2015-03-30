/**
 * 
 */
package ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Nick James
 *
 */
public class ImagePreview {
	JFrame frame;
	
	/**
	 * Constructor
	 * @param image - the image to preview
	 */
	public ImagePreview(Image image) {

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(image));
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(label);
		frame.pack();
		frame.setLocation(200, 200);
	}
	
	/**
	 * Sets the visibility of the preview window
	 * @param visible
	 */
	public void setVisible(boolean visible){
		frame.setVisible(visible);
	}
}
