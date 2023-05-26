
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.lawrence.FactorialUsingStreams;

class FactorialUsingStreamsTest {

	@Test
	 void factorialUsingStreams_Equals_Test() {

		FactorialUsingStreams assignment1 = new FactorialUsingStreams();

		Assertions.assertEquals(120, assignment1.factorialUsingStreams(5));
	}

	@Test
	void factorialUsingStreams_NotEquals_Test() {

		FactorialUsingStreams assignment1 = new FactorialUsingStreams();

		Assertions.assertNotEquals(100, assignment1.factorialUsingStreams(5));
	}
}
