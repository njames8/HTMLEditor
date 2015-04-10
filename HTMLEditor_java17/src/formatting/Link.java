/**
 * 
 */
package formatting;

import java.util.ArrayList;

/**
 * @author Nick James
 * Represents a link from an <a href> tag
 */
public class Link implements Comparable<Link> {
	/**
	 * the link of the tag
	 */
	private String link;
	
	/**
	 * the line numbers of the links
	 */
	private ArrayList<Integer> lineNums;
	
	/**
	 * constructor
	 * @param l - the link
	 */
	public Link(String l){
		this.link = l;
	}
	
	/**
	 * gets the link of this link
	 * @return - string of this link
	 */
	public String getLink(){
		return this.link;
	}
	
	/**
	 * adds a line num to the list 
	 * @param num
	 */
	public void addLineNum(int num){
		lineNums.add(num);
	}

	/**
	 * comparator for the links
	 */
	@Override
	public int compareTo(Link o) {
		// TODO Auto-generated method stub
		return this.link.compareTo(o.link);
		
	}
	
	
}
