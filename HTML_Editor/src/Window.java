import javax.swing.*;

public class Window extends javax.swing.JFrame {
	public Window() {
		init();
	}
	
	private void init() {
		setTitle("HTML Editor");
		setSize(640, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Window w = new Window();
		w.setVisible(true);
	}
}
