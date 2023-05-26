package com.lawrence.arithmeticoperations;

import java.util.List;

public class ArithmeticOperations {

	public int add(int x, int y) {
		return x + y;
	}

	public int add(List<Integer> integers) {
		return integers.stream().reduce(0, (a, b) -> a + b);
	}

	public int substract(int x, int y) {
		return x - y;
	}

	public int multiply(int x, int y) {
		return x * y;
	}

	public int divide(int x, int y) {
		return x / y;
	}
}
