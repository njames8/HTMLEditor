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
				return i - 1;
		}
		return -1;//failed
	}

	@Override
	public void update(Tab tab) {
		String text = tab.getText();
		lineLengths = new ArrayList<Integer>();
		int sum = 0;
		tab.head.traverseForLineNumbers(0);
		for (String line : text.split("\n")) {
			sum += line.length();
			if(line.length() == 0){
System.out.println("line.length == 0");
				tab.head.addToLineNum(1, lineLengths.size());
			}
			lineLengths.add(sum);
		}
	}
}
