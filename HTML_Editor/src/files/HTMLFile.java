package files;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;

import ui.EditorWindow;
import ui.Tab;
import cmd.SaveAsFile;

/*
 * Represents a file containing text. The file does not have to be on the disk,
 * allowing us to create them on the fly and store them locally in memory until they
 * are either saved or discarded.
 */
public class HTMLFile {
	private String location, text, name;
	private boolean needsToBeSaved;
	
	public HTMLFile() {
		location = null;
		text = "";
		name = "New File";
		needsToBeSaved = true;
	}
	
	// Updates our text buffer and marks the file as needing a save.
	// FIXME: only mark files as needing to be saved if they have changed since
	// the last save / load
	public void UpdateText(String text) {
		this.text = text;
		needsToBeSaved = true;
	}
	
	// Updates the file's name to reflect what it is on the disk
	private void UpdateName() {
		name = (new File(location)).getName();
	}
	
	// This is the name that should appear in the tab
	public String GetTabName() {
		return (needsToBeSaved? "* " : "") + name;
	}
	
	public String getText(){
		return this.text;
	}
	
	// Loads an existing file 
	public boolean Load(String location) {
		FileReader fr;
		
		try {
			fr = new FileReader(location);
		} catch(FileNotFoundException e) {
			// TODO: tell the user there was an error
			fr = null;
			return false;
		}
		
		// Try reading in the file
		try {
			BufferedReader in = new BufferedReader(fr);
	        String str;
	        while ((str = in.readLine()) != null) {
	            this.text +=str + "\n";
	        }
	        
	        System.out.print(this.text);
			
			fr.close();
			in.close();
		} catch (IOException e) {
			// TODO: tell the user there was an error
			e.printStackTrace();
			return false;
		}
		
		// If loading the file was successful, then we know the file location
		// we were given is valid
		this.location = location;
		this.needsToBeSaved = false;
		
		UpdateName();
		
		return true;
	}
	
	public void Save(EditorWindow w, Tab t) {
		if(!this.needsToBeSaved)
			return;
		// A null location means this "file" only exists in memory
		if(location == null) {
			// Display the save as dialog instead
			// TODO: call save as command
			SaveAsFile s = new SaveAsFile(new JFileChooser(), w, t);
			s.execute();
			return;
		}
		
		Writer fw;
		
		// Open and write the file
		try {
			System.out.println(location);
			fw = new BufferedWriter(new FileWriter(location));
			
			String [] textSep = text.split("\n");
			
			
			
			for(int x = 0; x < textSep.length; x++){
				System.out.println(textSep[x]);
				fw.write(textSep[x]);
				fw.write("\r\n"); //new line
			}
			
			
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		this.needsToBeSaved = false;
		cmd.UpdateText u = new cmd.UpdateText(t, (JTabbedPane)t.getParent());
		u.execute();
	}
	
	// Gets provided a location via the save as dialog
	public void SaveAs(String location, EditorWindow w, Tab t) {
		this.location = location;
		UpdateName();
		Save(w, t);
	}
	public void setLocation(String l){
		this.location = l;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

}