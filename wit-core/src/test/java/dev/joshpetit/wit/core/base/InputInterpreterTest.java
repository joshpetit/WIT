package dev.joshpetit.wit.core.base;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import dev.joshpetit.wit.core.commands.*;

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
			interpreter.newLineDenotation(false);
			ByteArrayInputStream in = new ByteArrayInputStream("abcdefghij".getBytes());
			interpreter.setInputStream(in);
			interpreter.setMappings();
			assertEquals(interpreter.getMappings(), expectedMapping);


			interpreter.newLineDenotation(true);
			in = new ByteArrayInputStream("a\nb\nc\nd\ne\nf\ng\nh\ni\nj\n".getBytes());
			interpreter.setInputStream(in);
			interpreter.setMappings();
			assertEquals(interpreter.getMappings(), expectedMapping);
		}

		@Test
		void testInput() {
			StringBuilder expected = new StringBuilder();
			TypingSystem system = new TypingSystem(config, context) {

				public void input(int key) {
					expected.append(key);
				}

				public void executeCommand(String key) {

				}
			};
			BasicCLInterpreter interpreter = new BasicCLInterpreter(system);

			ByteArrayInputStream mappings = new ByteArrayInputStream("abcdefghij".getBytes());
			interpreter.setInputStream(mappings);
			interpreter.setMappings();

			ByteArrayInputStream inputs = new ByteArrayInputStream("aabb".getBytes());
			interpreter.setInputStream(inputs);
			interpreter.start();
			assertEquals(expected.toString(), "0011");

			expected.delete(0, expected.length());
			inputs = new ByteArrayInputStream("ajab".getBytes());
			interpreter.setInputStream(inputs);
			interpreter.start();
			assertEquals(expected.toString(), "0901");
		}
	}
}
