/**
 * 
 */
package cmd;

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
		ImagePreview img = new ImagePreview(path);
		img.setVisible(true);
	}
}
