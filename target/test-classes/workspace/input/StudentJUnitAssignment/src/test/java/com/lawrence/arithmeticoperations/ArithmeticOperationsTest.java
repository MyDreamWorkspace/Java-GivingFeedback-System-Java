package com.lawrence.arithmeticoperations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArithmeticOperationsTest {

	ArithmeticOperations arithmeticOperations;

	@BeforeEach
	void setUp() {
		arithmeticOperations = new ArithmeticOperations();
	}

	@Test
	void testAddWithTwoNumbers() {
		assertEquals(350, arithmeticOperations.add(100, 250));
	}

	@Test
	void testAddWithIncorrectInput() {
		assertNotEquals(350, arithmeticOperations.add(100, 150));
	}

	@Test
	void testAddListOfNumbers() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		assertEquals(15, arithmeticOperations.add(numbers));
	}

	@Test
	void testAddListOfNegativeNumbers() {
		List<Integer> numbers = Arrays.asList(-1, -2, -3, -4, -5);
		assertEquals(-15, arithmeticOperations.add(numbers));
	}

	@Test
	void testAddListOfNumbersWithIncorrectInput() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		assertNotEquals(350, arithmeticOperations.add(numbers));
	}

	@Test
	void testSubstractWithTwoNumbers() {
		assertEquals(100, arithmeticOperations.substract(300, 200));
	}

	@Test
	void testSubstractWithIncorrectInput() {
		assertNotEquals(500, arithmeticOperations.substract(300, 200));
	}

	@Test
	void testMultipleWithTwoNumbers() {
		assertEquals(80000, arithmeticOperations.multiply(400, 200));
	}

	@Test
	void testMultipleWithIncorrectInput() {
		assertNotEquals(500, arithmeticOperations.multiply(300, 200));
	}

	@Test
	void testDivisionWithTwoNumbers() {
		assertEquals(2, arithmeticOperations.divide(400, 200));
	}

	@Test
	void testDivisionWithIncorrectInput() {
		assertNotEquals(500, arithmeticOperations.divide(300, 200));
	}

}
