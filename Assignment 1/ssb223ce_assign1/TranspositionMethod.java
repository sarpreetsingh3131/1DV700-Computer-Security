package assign1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TranspositionMethod {

	/* Private Fields */
	private Scanner scan = new Scanner(System.in);
	private String plainText = "";
	private String text = "";
	private String key = "";
	private char temp[];
	private String newText = "";
	private ReadFile a = new ReadFile();
	private String input = "";

	/* Constructor */
	public TranspositionMethod() {

	}

	/* Read File */
	public void readConsole() throws IOException {
		System.out.println("Please write the path of file: ");
		input = scan.nextLine();
		a.setPath(input);
		a.readFileText();
		plainText = a.getFileText();
		firstInstruction();

	}

	/* Encryption */
	private void encryption() {

		/*
		 * Make two substrings with key length. Then save it in array. Run the
		 * loop up to half of the array length and then replace the character
		 * positions.
		 */

		text = plainText.substring(key.length());
		text += plainText.substring(0, key.length());

		temp = new char[text.length()];

		for (int i = 0; i < text.length(); i++) {
			temp[i] = text.charAt(i);
		}

		for (int i = 0; i < temp.length / 2; i++) {
			char c = temp[i];
			temp[i] = temp[temp.length - 1 - i];
			temp[temp.length - 1 - i] = c;
		}
		newText = String.copyValueOf(temp);
		newText += key; // set key for safety
	}

	/* Decryption, opposite of encryption. */
	private void decryption() {

		text = plainText.substring(key.length());
		text += plainText.substring(0, key.length());

		temp = new char[text.length()];

		for (int i = 0; i < text.length(); i++) {
			temp[i] = text.charAt(i);
		}
		for (int i = temp.length / 2; i >= 0; i--) {
			char a = temp[i];
			temp[i] = temp[temp.length - 1 - i];
			temp[temp.length - 1 - i] = a;
		}
		newText = String.copyValueOf(temp);
	}

	/* User's instruction */
	private void firstInstruction() throws IOException {
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
						if (identifyKey(input) == true)
							break;
						else
							System.err.println("Incorrect key..");
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
				printer.print(newText);
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

	/* Check if key is too long */
	private boolean checkKey(String k) {
		return k.length() > plainText.length();

	}

	/* Check the key */
	private boolean identifyKey(String a) {
		return plainText.substring(plainText.length() - 1 - a.length()).contains(a);
	}

}