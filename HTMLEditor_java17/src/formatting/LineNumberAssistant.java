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
		lineLengths.add(0);
	}
	
	public int convertCharNumToLineNum(int charNum) {
		if (charNum == 0)
			return 0;
		for (int i = 0; i < lineLengths.size(); i += 1) {
			if (lineLengths.get(i) >= charNum)
				return i;
			if(i == lineLengths.size() - 1)
				return i + 1;
		}
		return -5;//failed
	}

	@Override
	public void update(Tab tab) {
		String text = tab.getText();
		lineLengths.clear();
		int sum = 0;
		if(tab.head != null)
			tab.head.traverseForLineNumbers();
		int i = 0;
		for (String line : text.split("\n")) {
			sum += line.length();
			if(line.length() == 0){
				tab.head.addToLineNum(1, i);
			}
			lineLengths.add(sum);
			i++;
		}
	}
}
