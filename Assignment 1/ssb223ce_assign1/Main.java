package assign1;

import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		/* User's instruction */
		Scanner scan = new Scanner(System.in);
		TranspositionMethod a = new TranspositionMethod();
		SubstitutionMethod b = new SubstitutionMethod();
		String input = "";

		System.out.println("Press 1 for substitution.\nPress 2 for transposition.");
		while (scan.hasNext()) {
			input = scan.nextLine();
			if (checkValue(input) == true) {
				if (Integer.valueOf(input) == 1) {
					b.readConsole();
					break;
				} else if (Integer.valueOf(input) == 2) {

					a.readConsole();
					break;
				} else

					System.err.println("Incorrect option.");
			} else if (checkValue(input) == false)
				System.err.println("Incorrect option.");

		}

		scan.close();
	}

	private static boolean checkValue(String a) {
		for (int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;

	}

}