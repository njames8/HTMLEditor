/**
 * 
 */
package formatting;


/**
 * @author Nick James
 *
 */
public class SelfClosingTag extends BaseTag{
	
	public SelfClosingTag(String t, int s, int e,  String l){
		this.tag = t;
		this.lineNumberStart = s;
		if(s == e)
			this.lineNumberEnd = e;
		else
			this.lineNumberEnd = s;
		this.link = l;
	}
	
	@Override
	public String getText(int indentLevel){
		String text = "";
		String t = "";
		//adds the number of indents that was sent
		for(int i = 0; i < indentLevel; i++){
			t += "    ";
		}
		//adds the tag name
		text += t + "<" + tag;
		if (link != null) {
			if (tag.equals("img")){
				text += " src=\"" + this.link + "\"";
			}else{
				text += " href=\"" + this.link + "\"" ;
			}
		}
			
		text += "/>\n";
		
		return text;
	}
	
	
	
	public String toString(){
		String text = "";
		text += this.getLineNumberStart() + "<" + tag;
		if (link != null) {
			if (tag.equals("img")){
				text += " src=\"" + this.link + "\"";
			}else{
				text += " href=\"" + this.link + "\"" ;
			}
		}
		text += "/>\n";
		return text; 
	}
}
