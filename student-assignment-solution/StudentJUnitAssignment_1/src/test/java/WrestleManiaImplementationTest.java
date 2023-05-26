import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.lawrence.WrestleEntity;
import com.lawrence.WrestleManiaImplementation;

public class WrestleManiaImplementationTest {

	@Test
	void testWrestleMania() {

		WrestleManiaImplementation wrestleManiaImplementation = new WrestleManiaImplementation();

		List<WrestleEntity> wrestlerlist = new ArrayList<>();

		WrestleEntity Wrestler1 = new WrestleEntity("John", 200);
		WrestleEntity Wrestler2 = new WrestleEntity("Lawrence", 220);
		WrestleEntity Wrestler3 = new WrestleEntity("Sunil", 240);
		WrestleEntity Wrestler4 = new WrestleEntity("Marina", 280);
		WrestleEntity Wrestler5 = new WrestleEntity("Alex", 310);

		wrestlerlist.add(Wrestler1);
		wrestlerlist.add(Wrestler2);
		wrestlerlist.add(Wrestler3);
		wrestlerlist.add(Wrestler4);
		wrestlerlist.add(Wrestler5);

		Assertions.assertEquals(1050, wrestleManiaImplementation.getSumOfWeight(wrestlerlist));
		
		Assertions.assertTrue(wrestleManiaImplementation.getWrestlerName(wrestlerlist).stream().anyMatch(w -> "Lawrence".equalsIgnoreCase(w)));
		
		Assertions.assertEquals(310, wrestleManiaImplementation.getMaxWeight(wrestlerlist));
	}
}