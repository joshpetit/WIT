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
	class BasicCLInterpreter {

		@Test
		void interactive() {
			Properties config = new Properties();
			try{
			config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));

			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
