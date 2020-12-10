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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;


class InputInterpreterTest {

	@Nested
	@DisplayName("Test BasicCLInterpreter")
	class BasicCLInterpreterTest {

		@Test
		void testMappingCreation() {
			Properties config = new Properties();
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
			try{
				config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));
				StringContext context = new StringContext();
				TypingSystem ts = new BasicTypingSystem(config, context);
				BasicCLInterpreter interpreter = new BasicCLInterpreter(ts);
				ByteArrayInputStream in = new ByteArrayInputStream("a\nb\nc\nd\ne\nf\ng\nh\ni\nj".getBytes());
				interpreter.setInputStream(in);
				interpreter.setMappings();
				assertEquals(interpreter.getMappings(), expectedMapping);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
