package com.lawrence;

import java.util.stream.LongStream;
import java.util.Scanner;

public class FactorialUsingStreams {

	/**
	 * Please Write 2 Test Cases for the Method factorialUsingStreams: 1. Equal Test
	 * 2. Not Equal Test 
	 * 
	 * @param factorialNumber
	 * @return
	 */
	
	// Calculate the Factorial number 
	// E.g: 5! = 5*4*3*2*1 = 120
	public long factorialUsingStreams(int factorialNumber) {

		return LongStream.rangeClosed(1, factorialNumber).reduce(1, (x, y) -> x * y);
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the Number you want: ");

		int userInput = scanner.nextInt();

		FactorialUsingStreams assignment1 = new FactorialUsingStreams();

		long myFactorial = assignment1.factorialUsingStreams(userInput);

		
		System.out.print("Factorial of : " + userInput + " is " + myFactorial);
		
		scanner.close();

	}

}
