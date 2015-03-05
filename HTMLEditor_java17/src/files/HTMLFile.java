package files;

import java.io.*;

/*
 * Represents a file containing text. The file does not have to be on the disk,
 * allowing us to create them on the fly and store them locally in memory until they
 * are either saved or discarded.
 */
public class HTMLFile {
	private String location, name;
	private boolean needsToBeSaved;

	public HTMLFile() {
		location = null;
		name = "New File";
		needsToBeSaved = false;
	}

	// Name of the file
	public String getFileName() {
		return name;
	}

	public boolean getNeedsSave() {
		return needsToBeSaved;
	}
	
	// Updates the name of the file to match that of the location
	private void updateName() {
		name = (new File(location)).getName();
	}
	
	public void changed() {
		needsToBeSaved = true;
	}
	
	public boolean isOnDisk() {
		return location != null;
	}

	// Loads an existing file
	public String load(String location) throws FileNotFoundException {
		FileReader fr;
		fr = new FileReader(location);
		
		// Where the file is loaded into and returned
		String str = "";

		// Try reading in the file
		try {
			BufferedReader in = new BufferedReader(fr);
			String line;
			
			while ((line = in.readLine()) != null) {
				str += line + "\n";
			}

			fr.close();
			in.close();
		} catch (IOException e) {
			// TODO: tell the user there was an error
			e.printStackTrace();
			return null;
		}

		// If loading the file was successful, then we know the file location
		// we were given is valid
		this.location = location;
		this.needsToBeSaved = false;

		updateName();

		return str;
	}

	// FIXME: this shouldn't be coupled with tab
	public boolean save(String text) {
		/*
		// FIXME: do this logic elsewhere. saving should ONLY save
		// A null location means this "file" only exists in memory
		if (location == null) {
			// Display the save as dialog instead
			// TODO: call save as command
			SaveAsFile s = new SaveAsFile(t);
			s.execute();
			return;
		}*/

		Writer fw;

		// Open and write the file
		try {
			fw = new BufferedWriter(new FileWriter(location));

			/*for (String s : text.split("\n")) {
				fw.write(s);
				fw.write("\r\n"); // new line
			}*/
			
			fw.write(text);

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		this.needsToBeSaved = false;
		return true;
	}

	// Receives a location and the text to be saved
	public boolean saveAs(String location, String text) {
		this.location = location;
		
		updateName();
		
		try {
			File file;
			
			if (this.name.endsWith(".html")) {
				file = new File(this.name);
			} else {
				file = new File(this.name + ".html");
			}

			file.createNewFile();
			
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
			output.write(text);
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		this.needsToBeSaved = false;
		return true;
	}
}