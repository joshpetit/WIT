
package dev.joshpetit.wit.core.base;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import dev.joshpetit.wit.core.commands.*;

class WITSystemTest {

	@Nested
	@DisplayName("StringInterpreter + BasicTypingSystem + StringContext")
	class ComboUno {
		Properties config;

		@BeforeEach
		void setup() {
			try{
				config = new Properties();
				config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		@Test
		void correctOutput() {
			StringContext context = new StringContext();
			TypingSystem system = new BasicTypingSystem(config, context);
			StringInterpreter interpreter = new StringInterpreter(system);

			interpreter.addMapping("zero", 0);
			interpreter.addMapping("one", 1);
			interpreter.addMapping("two", 2);
			interpreter.addMapping("three", 3);
			interpreter.addMapping("four", 4);
			interpreter.addMapping("five", 5);
			interpreter.addMapping("six", 6);
			interpreter.addMapping("seven", 7);
			interpreter.addMapping("eight", 8);
			interpreter.addMapping("nine", 9);

			interpreter.input("zero");
			interpreter.input("five");
			context.getText();
			assertEquals("a", context.getText());

			interpreter.input("zero");
			interpreter.input("six");
			assertEquals("ab", context.getText());

		}
	}
}
