import javax.swing.UIManager;
import cmd.OpenFile;
import ui.EditorWindow;

/**
 * Initializes the program
 * 
 * @author Jesse Roux
 *
 */
public class User {
	/**
	 * The main method to start the program
	 * @param args - the file name to open from the command line
	 */
	public static void main(String[] args) {

		// Try using the user's system theme
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			// We don't really care if we can't update the theme
		}
		
		EditorWindow w = EditorWindow.getInstance();
		w.setVisible(true);
		
		for(String s : args) {
			new OpenFile(s).execute();
		}
	}
}
