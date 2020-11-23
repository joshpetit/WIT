import static org.junit.jupiter.api.Assertions.assertEquals;
import dev.joshpetit.wit.gui.launcher.Main;

import org.junit.jupiter.api.Test;

class MainTest {
	@Test
	void working() {
		Main s = new Main();
		int res = s.lets();
		assertEquals(2, res);
	}
}
