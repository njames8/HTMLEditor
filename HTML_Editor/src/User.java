import javax.swing.UIManager;

import ui.EditorWindow;


public class User {
	public static void main(String[] args) {

		// Try using the user's system theme
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			// We don't really care if we can't update the theme
		}
		
		EditorWindow w = new EditorWindow();
		w.setVisible(true);
	}
}
