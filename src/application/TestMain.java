package application;

import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a value: ");
		String value = sc.next();
		// String value1 = "2.2f";
		
		Object object = value;
		// Float object1 = Float.parseFloat(value1);
		if (isInteger(value)) {
			object = Integer.parseInt(value);
		} else if (isDouble(value) && !value.toString().contains("f")) {
			object = Double.parseDouble(value);
		} else if (isFloat(value)) {
			object = Float.parseFloat(value);
		}

		System.out.println("Type: " + object.getClass().getName() + ", Value: " + object);
		String strF = "2.25f";
		float f = Float.parseFloat(strF);
		Object fltObj = f;

		System.out.println("Is it equal to 2.25f > obj? " + object.equals(fltObj));

		// Float object + object1;
		

		sc.close();
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

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
