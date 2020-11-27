package dev.joshpetit.wit.core.interpret;
import static org.junit.jupiter.api.Assertions.*;
import dev.joshpetit.wit.core.base.Translator;
import dev.joshpetit.wit.core.interpret.*;
import dev.joshpetit.wit.core.model.*;

import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class MainTest {

	@Test
	void testStringSystem() {

		Properties config = new Properties();
		config.setProperty("00", "00"); // System Message, CAPSLOCK
		config.setProperty("01", "1aA"); // Append Command
		config.setProperty("10", "20"); // Delete Command, CHAR

		StringSystem s = new StringSystem(config);
		s.input(0, 0);
		s.input(0, 1);
		s.input(1, 0);
	}
}
