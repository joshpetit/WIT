import static org.junit.jupiter.api.Assertions.*;
import dev.joshpetit.wit.core.base.Translator;
import dev.joshpetit.wit.core.model.*;

import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class MainTest {
	@BeforeAll
	public static void setup() {
		Properties config = new Properties();
		config.setProperty("00", "0system_payload");
		config.setProperty("01", "1aA");
		config.setProperty("10", "2no_payload");
		Translator.setConfig(config);
	}

	@Test
	void testTranslation() {

		Command com = Translator.translate(0, 1);
		assertEquals(com.getType(),
				Command.TYPE.APPEND, "Inputted translation should be append");
		assertTrue(com instanceof AppendCommand, "Command should be of type AppendCommand");
		AppendCommand ac = (AppendCommand) com;
		assertEquals(ac.getLower(), "a");
		assertEquals(ac.getUpper(), "A");
	}
}
