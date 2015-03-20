/**
 * 
 */
package cmd;

import java.awt.Image;
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
	
	private String path;
	
	public ImagePreviewCMD(String path){
		this.path = path;
	}

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
			ImagePreview img = new ImagePreview(image);
			img.setVisible(true);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Image not found", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
