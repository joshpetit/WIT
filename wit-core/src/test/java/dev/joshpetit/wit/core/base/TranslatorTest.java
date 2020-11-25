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
		config.setProperty("00", "00"); // System Message, CAPSLOCK
		config.setProperty("01", "1aA"); // Append Command
		config.setProperty("10", "20"); // Delete Command, CHAR
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

		com = Translator.translate(0, 0);
		assertTrue(com instanceof SystemCommand, "Command should be of type SystemCommand");
		SystemCommand sc = (SystemCommand) com;
		assertEquals(sc.getCommand(), Command.SYSTEM.CAPS_LOCK);

		com = Translator.translate(1, 0);
		assertTrue(com instanceof DeleteCommand, "Command should be of type DeleteCommand");
		DeleteCommand dc = (DeleteCommand) com;
		assertEquals(dc.getCommand(), Command.DELETE.CHAR);
	}
}
