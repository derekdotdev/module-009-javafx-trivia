package application;

import java.util.ArrayList;

public class TriviaOptions {

	ArrayList<Object> triviaObjects;
	ArrayList<String> triviaLabels = new ArrayList<String>();

	public TriviaOptions(ArrayList<Object> obj) {
		try {
			triviaObjects = new ArrayList<Object>(obj);
			for (Object o : obj) {
				triviaLabels.add(String.valueOf(o));
				System.out.println(String.valueOf(o));
			}
		} catch (Exception ex) {
			System.out.println("Error creating triviaObjects from tempList: " + ex.getMessage());
			System.out.println(ex.getStackTrace());
		}
	}

	public ArrayList<Object> getTriviaObjects() {
		return triviaObjects;
	}

	public ArrayList<String> getTriviaLabels() {
		return triviaLabels;
	}

	public boolean compareItem(Object a) {
		String tempString1 = null, tempString2 = null;
		Integer tempInt1 = 0, tempInt2 = 0;
		Float tempFloat1 = 0f, tempFloat2 = 0f;
		Double tempDouble1 = 0d, tempDouble2 = 0d;

		// System.out.println("Does it contain f? " + triviaObjects.get(0).toString());

		if (triviaObjects.get(0) instanceof Integer) {

			tempInt1 = (Integer) triviaObjects.get(0);
		}

		else if (triviaObjects.get(0) instanceof Double && !triviaObjects.get(0).toString().contains("f")) {

			tempDouble1 = (Double) triviaObjects.get(0);
		}

		else if (triviaObjects.get(0) instanceof Float) {

			tempFloat1 = (Float) triviaObjects.get(0);
		}

		else if (triviaObjects.get(0) instanceof String) {

			tempString1 = (String) triviaObjects.get(0);
		}


		if (triviaObjects.get(1) instanceof Integer) {

			tempInt2 = (Integer) triviaObjects.get(1);
		}

		else if (triviaObjects.get(1) instanceof Double && !triviaObjects.get(1).toString().contains("f")) {

			tempDouble2 = (Double) triviaObjects.get(1);
		}

		else if (triviaObjects.get(1) instanceof Float) {

			tempFloat2 = (Float) triviaObjects.get(1);
		}

		else if (triviaObjects.get(1) instanceof String) {

			tempString2 = (String) triviaObjects.get(1);
		}

		// Objects to hold possible variables
		Object tempAnswer1 = tempInt1 + tempInt2;
		Object tempAnswer2 = tempInt1 + tempString2;
		Object tempAnswer3 = tempInt1 + tempFloat2;
		Object tempAnswer4 = tempInt1 + tempDouble2;
		Object tempAnswer5 = tempDouble1 + tempInt2;
		Object tempAnswer6 = tempDouble1 + tempString2;
		Object tempAnswer7 = tempDouble1 + tempFloat2;
		Object tempAnswer8 = tempDouble1 + tempDouble2;
		Object tempAnswer9 = tempString1 + tempInt2;
		Object tempAnswer10 = tempString1 + tempString2;
		Object tempAnswer11 = tempString1 + tempFloat2;
		Object tempAnswer12 = tempString1 + tempDouble2;
		Object tempAnswer13 = tempFloat1 + tempInt2;
		Object tempAnswer14 = tempFloat1 + tempString2;
		Object tempAnswer15 = tempFloat1 + tempFloat2;
		Object tempAnswer16 = tempFloat1 + tempDouble2;


		// Check for true answer
		if (tempAnswer1.equals(a) || tempAnswer2.equals(a) || tempAnswer3.equals(a) || tempAnswer4.equals(a)
				|| tempAnswer5.equals(a) || tempAnswer6.equals(a) || tempAnswer7.equals(a) || tempAnswer8.equals(a)
				|| tempAnswer9.equals(a) || tempAnswer10.equals(a) || tempAnswer11.equals(a) || tempAnswer12.equals(a)
				|| tempAnswer13.equals(a) || tempAnswer14.equals(a) || tempAnswer15.equals(a)
				|| tempAnswer16.equals(a)) {
			return true;
		}
		return false;
	}

	// Method which will only parseFloat a String
	// which doesn't contain JUST characters
	public float parseForFloat(String strA) {
		if (isOnlyLetters(strA)) {
			return 0.0f;
		}
		return Float.parseFloat(strA);
	}

	// Method to check if a string only contains letters
	// (Cannot call Float.parseFloat() on a string)
	private boolean isOnlyLetters(String s) {
		char c = ' ';
		boolean isGood = false, safe = isGood;
		int failCount = 0;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isLetter(c))
				isGood = true;
			else {
				isGood = false;
				failCount += 1;
			}
		}
		if (failCount == 0 && s.length() > 0)
			safe = true;
		else
			safe = false;
		return safe;
	}


	// Method to parse String for Integer
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	// Method to parse String for Float
	public static boolean isFloat(String value) {
		try {
			Float.parseFloat(value);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}
}


//My sad excuse to get FLOATS WORKING

/*
 * String value1 = triviaObjects.get(0).toString(); String value2 =
 * triviaObjects.get(1).toString();
 * 
 * if (isInteger(value1)) { tempInt1 = Integer.parseInt(value1); } else if
 * (isDouble(value1) && !value1.toString().contains("f")) { tempDouble1 =
 * Double.parseDouble(value1); } else if (isFloat(value1)) { tempFloat1 =
 * Float.parseFloat(value1); } else if (triviaObjects.get(0) instanceof String)
 * { tempString1 = (String) triviaObjects.get(0); // tempString1 = value1; }
 * 
 * if (isInteger(value2)) { tempInt2 = Integer.parseInt(value2); } else if
 * (isDouble(value2) && !value2.toString().contains("f")) { tempDouble2 =
 * Double.parseDouble(value2); } else if (isFloat(value2)) { tempFloat2 =
 * Float.parseFloat(value2); } else if (triviaObjects.get(1) instanceof String)
 * { tempString2 = (String) triviaObjects.get(1); // tempString2 = value2; }
 */

/*
 * // Check if object 'a' is a String (cannot parseFloat a string) String strA =
 * a.toString(); float fltA = parseForFloat(strA);
 * 
 * // Set object to parseFloat value Object objA = fltA;
 * 
 * // parseFloat tempAnswers to compare with parseFloat(ed) a.toString String
 * strTempAnswer13 = tempAnswer13.toString(); Object objTempAnswer13 =
 * Float.parseFloat(strTempAnswer13);
 * 
 * String strTempAnswer15 = tempAnswer15.toString(); Object objTempAnswer15 =
 * Float.parseFloat(strTempAnswer15);
 * 
 * String strTempAnswer16 = tempAnswer16.toString(); Object objTempAnswer16 =
 * Float.parseFloat(strTempAnswer16);
 */

/*
 * // Check for true answer if (tempAnswer1.equals(a) || tempAnswer2.equals(a)
 * || tempAnswer3.equals(a) || tempAnswer4.equals(a) || tempAnswer5.equals(a) ||
 * tempAnswer6.equals(a) || tempAnswer7.equals(a) || tempAnswer8.equals(a) ||
 * tempAnswer9.equals(a) || tempAnswer10.equals(a) || tempAnswer11.equals(a) ||
 * tempAnswer12.equals(a) || objTempAnswer13.equals(objA) ||
 * tempAnswer14.equals(a) || objTempAnswer15.equals(objA) ||
 * objTempAnswer16.equals(objA)) { return true; }
 */
