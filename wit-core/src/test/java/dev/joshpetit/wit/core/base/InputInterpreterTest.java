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
	@DisplayName("Test StreamInterpreter")
	class StreamInterpreterTest {
		Properties config;
		Commandable context;
		TypingSystem ts;
		@BeforeEach
		void setup() {
			try {
				config = new Properties();
				ts = new TypingSystem(config, context) {
					public void input(int key) {
					}
					public void executeCommand(String key) {
					}
					public boolean clear() {
						return false;
					}
				};
				config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));
				context = new Commandable() {
				};
			} catch (IOException e) {
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

			StreamInterpreter interpreter = new StreamInterpreter(ts);
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
				public boolean clear() {
					return false;
				}
				public void input(int key) {
					expected.append(key);
				}

				public void executeCommand(String key) {

				}
			};
			StreamInterpreter interpreter = new StreamInterpreter(system);

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

	@Nested
	@DisplayName("Test StringInterpreter")
	class StringInterpreterTest {
		Properties config;
		Commandable context;
		TypingSystem ts;

		@BeforeEach
		void setup() {
			try {
				config = new Properties();
				config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));
				context = new Commandable() {
				};
				ts = new TypingSystem(config, context) {
					public boolean clear() {
						return false;
					}
					public void input(int key) {
					}
					public void executeCommand(String key) {
					}
				};
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Test
		void testMappingCreation() {
			Map<String, Integer> expectedMapping = new HashMap<>();
			StringInterpreter interpreter = new StringInterpreter(ts);
			expectedMapping.put("zero", 0);
			expectedMapping.put("one", 1);
			expectedMapping.put("two", 2);
			expectedMapping.put("three", 3);
			expectedMapping.put("four", 4);
			expectedMapping.put("five", 5);
			expectedMapping.put("six", 6);
			expectedMapping.put("seven", 7);
			expectedMapping.put("eight", 8);
			expectedMapping.put("nine", 9);

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

			assertEquals(interpreter.getMappings(), expectedMapping);
		}

		@Test
		void testInput() {
			StringBuilder expected = new StringBuilder();
			TypingSystem system = new TypingSystem(config, context) {
				public boolean clear() {
					return false;
				}

				public void input(int key) {
					expected.append(key);
				}

				public void executeCommand(String key) {

				}
			};
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
			interpreter.input("zero");
			interpreter.input("three");
			interpreter.input("nine");

			assertEquals("0039", expected.toString());
		}
	}
}
