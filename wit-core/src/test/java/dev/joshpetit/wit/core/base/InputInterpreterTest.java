package dev.joshpetit.wit.core.base;
import dev.joshpetit.wit.core.commands.*;
import java.io.IOException;

import java.util.Properties;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;


class InputInterpreterTest {

	@Nested
	@DisplayName("Test BasicCLInterpreter")
	class BasicCLInterpreterTest {

		@Test
		void interactive() {
			Properties config = new Properties();
			try{
			config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));
			StringContext context = new StringContext();
			TypingSystem ts = new BasicTypingSystem(config, context);
			BasicCLInterpreter interpreter = new BasicCLInterpreter(ts);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
