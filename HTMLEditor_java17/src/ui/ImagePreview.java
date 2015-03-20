/**
 * 
 */
package ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Nick James
 *
 */
public class ImagePreview {
	JFrame frame;
	
	public ImagePreview(String path) {
		Image image;

		JLabel label = new JLabel();

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();

		try {
			if (path.contains("http://") || path.contains("https://")) {
				URL url = new URL(path);
				image = ImageIO.read(url);
			} else {
				image = ImageIO.read(new File(path));
			}
			
			if (image.getHeight(null) >= height/2 || image.getWidth(null) >= width/2) {
				image = image.getScaledInstance(image.getWidth(null)/2,image.getHeight(null)/2, Image.SCALE_SMOOTH);
			}
			label.setIcon(new ImageIcon(image));
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.getContentPane().add(label);
		frame.pack();
		frame.setLocation(200, 200);
	}
	
	public void setVisible(boolean visible){
		frame.setVisible(visible);
	}
}
