/**
 * 
 */
package formatting;
import java.util.*;

import ui.Tab;

/**
 * @author adamwalsh
 *
 */
public class LineNumberAssistant implements ui.Observer {
	private List<Integer> lineLengths;
	public LineNumberAssistant() {
		this.lineLengths = new ArrayList<Integer>();
	}
	
	public int convertCharNumToLineNum(int charNum) {
		for (int i = 0; i < lineLengths.size(); i += 1) {
			if (lineLengths.get(i) > charNum)
				return i-1;
		}
		return -1;//failed
				
	}

	@Override
	public void update(Tab tab) {
		String text = tab.getText();
		lineLengths.clear();
		for (String line : text.split("\n")) {
			lineLengths.add(line.length());
		}
	}
}
