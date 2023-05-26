package com.lawrence.string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringOperationTest {

	private String sL;
	private String sR;
	private String sEmpty;

	@BeforeEach
	void SetupStringOperation() {
		sL = "left";
		sR = "right";
		sEmpty = "";
	}

	@Test
	void testConcat1() {
		String sOut = StringOperation.concat(sL, sR);
		assertEquals("leftright", sOut);
	}

	@Test
	void testConcat1NotEqual() {
		String sOut = StringOperation.concat(sL, sR);
		assertNotEquals("left", sOut);
	}

	@Test
	void testConcat2() {
		String sOut = StringOperation.concat(sEmpty, sL);
		assertEquals("left", sOut);
	}

	@Test
	void testConcat3() {
		String sOut = StringOperation.concat(sL, sEmpty);
		assertEquals("left", sOut);
	}

	@Test
	void testConcat3NotEqual() {
		String sOut = StringOperation.concat(sL, "123");
		assertNotEquals("left", sOut);
	}

	@Test
	void testReverse1() {
		String sOut = StringOperation.reverse(sEmpty);
		assertEquals(sEmpty, sOut);
	}
	
	@Test
	void testReverse1NotEqual() {
		String sOut = StringOperation.reverse(sEmpty + "123");
		assertNotEquals(sEmpty, sOut);
	}

	@Test
	void testReverse2() {
		String sOut = StringOperation.reverse("dcba");
		assertEquals("abcd", sOut);
	}
	
	@Test
	void testReverse2NotEqual() {
		String sOut = StringOperation.reverse("dcba");
		assertNotEquals("dcba", sOut);
	}

	@Test
	void testReverse3NotEqual() {
		String sOut = StringOperation.reverse("edcba");
		assertNotEquals("abcd", sOut);
	}
}
