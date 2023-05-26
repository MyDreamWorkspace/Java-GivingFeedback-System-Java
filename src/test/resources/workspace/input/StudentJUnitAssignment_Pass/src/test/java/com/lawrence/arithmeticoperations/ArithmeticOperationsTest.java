package com.lawrence.arithmeticoperations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
	void ttestMultipleWithIncorrectInput() {
		assertNotEquals(500, arithmeticOperations.multiply(300, 200));
	}
	
	@Test
	void testDivisionWithTwoNumbers() {
		assertEquals(2, arithmeticOperations.divide(400, 200));
	}
	
	@Test
	void ttestDivisionWithIncorrectInput() {
		assertNotEquals(500, arithmeticOperations.divide(300, 200));
	}

}
