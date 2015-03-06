package files;

import java.io.*;

/*
 * Represents a file containing text. The file does not have to be on the disk,
 * allowing us to create them on the fly and store them locally in memory until they
 * are either saved or discarded.
 */
public class HTMLFile {
	/**
	 * The location of the file
	 */
	private String location;
	
	/**
	 * The name of the file
	 */
	private String name;
	
	/**
	 * Does this file need to be saved?
	 */
	private boolean needsToBeSaved;

	/**
	 * Constructor
	 */
	public HTMLFile() {
		location = null;
		name = "New File";
		needsToBeSaved = false;
	}

	/**
	 *  Gets the Name of the file
	 * @return String - the name of the file
	 */
	public String getFileName() {
		return name;
	}
	
	/**
	 * Does this file need to be saved?
	 * @return - Boolean - Does this file need to be saved?
	 */
	public boolean getNeedsSave() {
		return needsToBeSaved;
	}
	
	/**
	 * Updates the name of the file to match that of the location
	 */
	private void updateName() {
		name = (new File(location)).getName();
	}
	
	/**
	 * Puts the file in a state of need
	 * to be saved
	 */
	public void changed() {
		needsToBeSaved = true;
	}
	
	/**
	 * Does the actual HTML file exist on the disk?
	 * @return - true if the file exists, false otherwise
	 */
	public boolean isOnDisk() {
		return location != null;
	}

	/**
	 *  Loads an existing file
	 * @param location - The path to the file
	 * @return - The text in the file
	 * @throws FileNotFoundException
	 */
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

	/**
	 * Saves the file
	 * @param text - the text to be saved
	 * @return - true if saved, false otherwise
	 */
	public boolean save(String text) {
		if(!needsToBeSaved)
			return true;

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

	/**
	 * Receives a location and the text to be saved
	 * @param location- the path to the file
	 * @param text - the text to be written to the file
	 * @return - true if saved, false otherwise
	 */
	public boolean saveAs(String location, String text) {
		File file;
		
		try {
			file = new File(location);

			file.createNewFile();
			
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
			output.write(text);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		this.location = location;
		this.name = file.getName();
		this.needsToBeSaved = false;
		return true;
	}
}