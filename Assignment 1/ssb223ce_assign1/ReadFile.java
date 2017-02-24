package assign1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadFile {

	/* Fields */
	private String filePath = "";
	private String read = "";
	private String fileName = "";

	/* Constructor */
	public ReadFile() {

	}

	/* Another constructor with parameter */
	public ReadFile(String path) {
		filePath = path;
	}

	/* Getter and setters */
	public void setPath(String path) {
		filePath = path;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileName() {
		return fileName;
	}

	/* Get all the text from file */
	public String getFileText() {
		return read;
	}

	/* Check if file exists */
	public boolean checkIfFileExists() {
		File file = new File(filePath);
		if (file.exists()) {
			fileName = file.getName();
			return true;
		}
		return false;
	}

	/* Read file */
	public void readFileText() throws FileNotFoundException {
		if (checkIfFileExists() == true) { // first check if it exists

			File file = new File(filePath);

			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {

				read = read + scan.nextLine() + "\n";

			}

			scan.close();

		}

		else
			throw new FileNotFoundException("No file found"); // throw error if
																// file does not
																// exists

	}

	/* Print text */
	public void printFileText() {
		System.out.println("Printing text from file: " + fileName);
		System.out.println(read);
	}

}