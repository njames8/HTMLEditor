/**
 * 
 */
package formatting;

import java.util.ArrayList;

/**
 * @author Nick James
 *
 */
public class Link implements Comparable<Link> {
	private String link;
	private ArrayList<Integer> lineNums;
	
	public Link(String l){
		this.link = l;
	}
	
	public String getLink(){
		return this.link;
	}
	
	public void addLineNum(int num){
		lineNums.add(num);
	}

	@Override
	public int compareTo(Link o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
