package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInput {

	// Set path to input.txt for final submission (on PC, place at C:\)
	// private static final String filepath = "C:\";

	// Path to input.txt on my Mac during production
	// private static final String filepath =
	// "module-9-javafxml-trivia-final/src/application/input.txt";
	private static final String filepath = "/Users/derekdileo/Documents/Software Development/Workspaces/Java-Programming-For-Beginners/module-9-javafxml-trivia-final/src/application/input.txt";

	public void testClass() throws FileNotFoundException {
		File file = new File(filepath);
		Scanner sc = new Scanner(file);
		ArrayList<Object> testList = new ArrayList<Object>();

		while (sc.hasNext()) {
			testList.add(sc.next());
		}
		sc.close();
	}

	Scanner sc;

	public ArrayList<TriviaOptions> getFile() {

		try {
			// Load input.txt
			File file = new File(filepath);

			// Set counter for question data input (6 items per question)
			int counter = 0;
			sc = new Scanner(file);

			ArrayList<Object> tempList = new ArrayList<Object>();

			ArrayList<TriviaOptions> tempOptions = new ArrayList<TriviaOptions>();

			// Scan each value
			while (sc.hasNext()) {

				// Six at a time
				while (sc.hasNext() && counter < 6) {
					// Check for data type
					if (sc.hasNextInt()) {
						tempList.add(sc.nextInt());
					} else if (sc.hasNextDouble()) {
						tempList.add(sc.nextDouble());
						// Floats made this project very difficult...
						// (See TriviaOptions)
					} else {
						tempList.add(sc.next());
					}
					counter++;
				}
				tempOptions.add(new TriviaOptions(tempList));

				counter = 0;
				tempList.clear();
			}
			return tempOptions;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error thrown in getFile filling arrayList");
		}
		sc.close();
		return null;
	}
}
