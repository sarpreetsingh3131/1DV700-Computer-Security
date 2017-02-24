package assign1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SubstitutionMethod {

	/* Fields */
	private Scanner scan = new Scanner(System.in);
	private ReadFile read = new ReadFile();
	private String plainText = "";
	private StringBuilder text = new StringBuilder();
	private char ch;
	private String input = "";
	private String key = "";

	/* Constructor */
	public SubstitutionMethod() {

	}

	/* Read the file */
	public void readConsole() throws IOException {
		System.out.println("Please write the path of file: ");
		input = scan.nextLine();
		read.setPath(input);
		read.readFileText();
		plainText = read.getFileText();
		begininingInstruction();
}

	/* Encrypt the file, with the help of key value. */
	private void encryption() {
		for (int i = 0; i < plainText.length(); i++) {
			ch = plainText.charAt(i);
			ch += key.length();
			text.append(ch);
		}
	}

	/* Decrypt the file, with the help of key value. */
	private void decryption() {
		for (int i = 0; i < plainText.length(); i++) {
			ch = plainText.charAt(i);
			ch -= key.length();
			text.append(ch);
		}
	}

	/* User's instruction */
	private void begininingInstruction() throws IOException {
		System.out.println("Press 1 for encryption.\nPress 2 for decryption.");
		while (scan.hasNext()) {
			input = scan.nextLine();
			if (checkValue(input) == true) {
				if (Integer.valueOf(input) == 1) {
					System.out.println("Please type your key.");
					while (scan.hasNext()) {
						input = scan.nextLine();
						if (checkKey(input) == false)
							break;
						else
							System.err.println("Maximun key length is 8. Your key length is " + (input.length() - 1));
					}
					key = input;
					encryption();
					System.out.println("Your file has been encrypted successfully.");
					break;
				} else if (Integer.valueOf(input) == 2) {
					System.out.println("Please type your key.");
					while (scan.hasNext()) {
						input = scan.nextLine();
						if (checkKey(input) == false)
							break;
						else
							System.err.println("Maximun key length is 8. Your key length is " + (input.length() - 1));
					}
					key = input;
					decryption();
					System.out.println("Your file has been decrypted successfully.");
					break;
				}

				else
					System.err.println("Incorrect option.");
			} else if (checkValue(input) == false)
				System.err.println("Incorrect option.");

		}
		writeFile();

	}

	/* Method which write file and throw error on wrong input */
	private void writeFile() throws IOException {
		System.out.println("Please write the file path for saving the file.");
		while (scan.hasNext()) {
			input = scan.nextLine();
			File file = new File(input);
			if (file.exists()) {
				System.err.println(file.getName() + " already exists. Please try new path.");
			} else if (file.isAbsolute() && !file.exists()) {
				file.createNewFile();
				PrintWriter printer = new PrintWriter(file);
				printer.print(text);
				printer.close();
				file.setReadOnly();
				System.out.println("File created successfully." + file.getAbsolutePath());
				System.out.println("Program ends.");
				System.exit(1);

			} else if (!file.isAbsolute()) {
				System.err.println("Incorrect path. Please write correct path.");
			}
		}

	}

	/* For preventing unwanted input */
	private boolean checkValue(String a) {
		for (int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;

	}

	/* Encapsulation */
	private boolean checkKey(String a) {
		return a.length() > 8;
	}
}