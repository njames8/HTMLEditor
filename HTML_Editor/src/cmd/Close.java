/**
 * Represents the call to close the editor window
 */
package cmd;

/**
 * @author Nick James
 *
 */
public class Close implements Command {

	public Close() {
		/**
		 * Nothing is needed here Close does not have any properties
		 */
	}

	@Override
	public void execute() {
		/**
		 * Closes the application
		 */
		System.exit(0);
	}

}
