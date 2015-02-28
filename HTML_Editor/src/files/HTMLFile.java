package files;

import java.io.*;

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
	            this.text +=str;
	        }
	        
	        System.out.print(this.text);
			
			fr.close();
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
	
	public void Save() {
		if(!this.needsToBeSaved)
			return;
		
		// A null location means this "file" only exists in memory
		if(location == null) {
			// Display the save as dialog instead
			// TODO: call save as command
			
		}
		
		FileWriter fw;
		
		// Open and write the file
		try {
			fw = new FileWriter(location);
			fw.write(this.text);
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		this.needsToBeSaved = false;
	}
	
	// Gets provided a location via the save as dialog
	public void SaveAs(String location) {
		this.location = location;
		UpdateName();
		Save();
	}
	public void setLocation(String l){
		this.location = l;
	}

}
