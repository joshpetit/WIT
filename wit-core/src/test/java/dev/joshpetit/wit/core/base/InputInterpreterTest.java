package dev.joshpetit.wit.core.base;
import dev.joshpetit.wit.core.commands.*;
import java.io.IOException;
import java.io.ByteArrayInputStream;

import java.util.Properties;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;


class InputInterpreterTest {

	@Nested
	@DisplayName("Test BasicCLInterpreter")
	class BasicCLInterpreterTest {
		Properties config;
		StringContext context;
		TypingSystem ts;
		@BeforeEach
		void setup() {
			try{
				config = new Properties();
				config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));
				context = new StringContext();
				ts = new BasicTypingSystem(config, context);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		@Test
		void testMappingCreation() {
			Map<String, Integer> expectedMapping = new HashMap<>();
			expectedMapping.put("a", 0);
			expectedMapping.put("b", 1);
			expectedMapping.put("c", 2);
			expectedMapping.put("d", 3);
			expectedMapping.put("e", 4);
			expectedMapping.put("f", 5);
			expectedMapping.put("g", 6);
			expectedMapping.put("h", 7);
			expectedMapping.put("i", 8);
			expectedMapping.put("j", 9);
			BasicCLInterpreter interpreter = new BasicCLInterpreter(ts);
			ByteArrayInputStream in = new ByteArrayInputStream("a\nb\nc\nd\ne\nf\ng\nh\ni\nj".getBytes());
			interpreter.setInputStream(in);
			interpreter.setMappings();
			assertEquals(interpreter.getMappings(), expectedMapping);
		}
	}
}
