import static org.junit.jupiter.api.Assertions.*;
import dev.joshpetit.wit.core.base.Translator;
import dev.joshpetit.wit.core.model.Command;

import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class MainTest {
	@BeforeAll
	public static void setup() {
		Properties config = new Properties();
		config.setProperty("00", "0system_payload");
		config.setProperty("01", "1append_payload");
		config.setProperty("10", "2no_payload");
		Translator.setConfig(config);
	}

	@Test
	void testTranslation() {
		Command com = Translator.translate(0, 0);
		assertEquals(com.getType(),
				Command.SYSTEM, "inputted translation should be system command");
		assertEquals(com.getPayload(),
				"system_payload", "System payload message should be command payload");

		com = Translator.translate(0, 1);
		assertEquals(com.getType(),
				Command.APPEND, "Inputted translation should be append");
		assertEquals(com.getPayload(),
				"append_payload", "Payload message should be append_payload");

		com = Translator.translate(1, 0);
		assertEquals(com.getType(),
				Command.DELETE, "Inputted translation should be delete");
		assertFalse(com.containsPayload(), "Delete messages do not have payloads");
	}
}
