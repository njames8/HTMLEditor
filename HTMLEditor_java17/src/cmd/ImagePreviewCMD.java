/**
 * 
 */
package cmd;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import ui.ImagePreview;

/**
 * @author Nick James
 *
 */
public class ImagePreviewCMD implements Command{
	
	/**
	 * The path to the image to preview
	 */
	private String path;

	
	/**
	 * Constructor
	 * @param path - path to the preview image
	 */
	public ImagePreviewCMD(String path){
		this.path = path;
	}
	
	/**
	 * Fetches image from Internet or local disk
	 * Displays image preview
	 * 	
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Image image;
		try {
			if (path.contains("http://") || path.contains("https://")) {
				URL url = new URL(path);
				image = ImageIO.read(url);
			} else {
				image = ImageIO.read(new File(path));
			}
			
			Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int)screensize.getWidth();
			int height = (int)screensize.getHeight();
				
			while (image.getHeight(null) >= height/2 || image.getWidth(null) >= width/2) {
				image = image.getScaledInstance(image.getWidth(null)/2,image.getHeight(null)/2, Image.SCALE_SMOOTH);
			}
			
			ImagePreview img = new ImagePreview(image);
			img.setVisible(true);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Image not found", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
