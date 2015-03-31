/**
 * 
 */
package formatting;

import java.util.ArrayList;

/**
 * @author Nick James
 *
 */
public class SelfClosingTag extends BaseTag{
	
	public SelfClosingTag(String t, int s, int e, boolean c,  String l){
		this.tag = t;
		this.lineNumberStart = s;
		this.lineNumberEnd = e;
		this.link = l;
		
	}
}
