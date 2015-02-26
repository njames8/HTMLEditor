package files;

import java.io.*;

/*
 * Represents a file containing text. The file does not have to be on the disk,
 * allowing us to create them on the fly and store them locally in memory until they
 * are either saved or discarded.
 */
public class HTMLFile {
	private String location, text;
	private boolean needsToBeSaved;
	private File file;
	
	public HTMLFile() {
		location = "";
		text = "";
		needsToBeSaved = true;
		
		file = null;
	}
	
	public HTMLFile(String location) {
		this.location = location;
		needsToBeSaved = false;
		
		Load();
	}
	
	public void UpdateText(String text) {
		this.text = text;
		needsToBeSaved = true;
	}
	
	private boolean Load() {
		file = new File(location);
		
		if(file == null) 
			return false;
		
		FileReader fr;
		
		try {
			fr = new FileReader(file);
		} catch(FileNotFoundException e) {
			file = null;
			return false;
		}

		// read characters in
		
		return true;
	}
	
	public void Save() {
		
	}
}
