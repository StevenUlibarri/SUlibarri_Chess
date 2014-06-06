package chess.controller;

import java.util.Scanner;

public class MyIO {

	private Scanner in = new Scanner(System.in);
	
	public int promptInt(String message) {
		final int MIN = Integer.MIN_VALUE;
		final int MAX = Integer.MAX_VALUE;
		
		int input = 0;
		boolean isValid = false;
		
		System.out.println(message);
		
		while(!isValid) {
			try {
				input = Integer.parseInt(in.nextLine());
				if(input > MAX || input < MIN) {
					throw new Exception();
				}
				else {
					isValid = true;
				}
			}
			catch (Exception e) {
				System.out.println("Invalid Input");
				System.out.println("Please entr an Integer between " + MIN
						+ " and " + MAX + ".");
			}
		}
		return input;
	}
	
	public int promptIntInRange(String message, int min, int max) {
		final int MIN = min;
		final int MAX = max;
		
		int input = 0;
		boolean isValid = false;
		
		System.out.println(message);
		
		while(!isValid) {
			try {
				input = Integer.parseInt(in.nextLine());
				if(input > MAX || input < MIN) {
					throw new Exception();
				}
				else {
					isValid = true;
				}
			}
			catch (Exception e) {
				System.out.println("Invalid Input");
				System.out.println("Please entr an Integer between " + MIN
						+ " and " + MAX + ".");
			}
		}
		return input;
	}
}
