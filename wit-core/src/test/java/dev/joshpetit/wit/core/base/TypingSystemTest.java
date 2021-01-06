package dev.joshpetit.wit.core.base;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import dev.joshpetit.wit.core.commands.*;

class TypingSystemTest {

	@Nested
	@DisplayName("Tests for String Context")
	class BasicTypingSystemTest {

		@Test
		void testMethodCalls() {
			boolean[] called = new boolean[3];
			BasicCommandable context = new BasicCommandable() {
				public void typingMessage(MessageCommand c) {
					called[0] = true;
				}
				public void typingDelete(DeleteCommand c) {
					called[1] = true;
				}
				public void typingAppend(AppendCommand c) {
					called[2] = true;
				}
				public boolean nextUpper() {
					return true;
				}
				Map<String, Integer> s = new HashMap<>();
			};
			Properties props = new Properties();
			props.setProperty("00", "00");
			props.setProperty("01", "10");
			props.setProperty("02", "2aA");
			//bts lol, my sister would scream
			BasicTypingSystem bts = new BasicTypingSystem(props, context);
			bts.input(0);
			bts.input(0);
			assertTrue(called[0], "typingMessage should have been called");
			bts.input(0);
			bts.input(1);
			assertTrue(called[1], "typingDelete command should have been called");
			bts.input(0);
			bts.input(2);
			assertTrue(called[2], "typingAppend command should have been called");
		}

		@Test
		void testStandardCompletions() {
			boolean[] called = new boolean[3];
			final boolean[] upper = new boolean[] {false};
			BasicCommandable context = new BasicCommandable() {
				public void typingMessage(MessageCommand c) {}
				public void typingDelete(DeleteCommand c) {}
				public void typingAppend(AppendCommand c) {}
				public boolean nextUpper() {
					return upper[0];
				}
			};
			Properties props = BasicTypingSystem.getDefaultProperties();
			BasicTypingSystem bts = new BasicTypingSystem(props, context);
			Map comps = Map.of(
			                0, "a-e",
			                1, "f-j",
			                2, "k-o",
			                3, "p-t",
			                4, "u-y",
			                5, "",
			                6, "",
			                7, "",
			                8, "",
			                9, "SPACES"
			            );
			assertEquals(comps, bts.getStandardCompletions());
			bts.input(9);
			comps = Map.of(
			            0, "",
			            1, "",
			            2, "",
			            3, "",
			            4, "z",
			            5, "ENTER",
			            6, "N.L",
			            7, "TAB",
			            8, "SPACE",
			            9, "0"
			        );
			assertEquals(comps, bts.getStandardCompletions());
			bts.input(9);

			upper[0] = true;
			comps = Map.of(
			            0, "A-E",
			            1, "F-J",
			            2, "K-O",
			            3, "P-T",
			            4, "U-Y",
			            5, "",
			            6, "",
			            7, "",
			            8, "",
			            9, "SPACES"
			        );
			assertEquals(comps, bts.getStandardCompletions());

			bts.input(4);
			comps = Map.of(
			            0, "",
			            1, "",
			            2, "",
			            3, "",
			            4, "5",
			            5, "U",
			            6, "V",
			            7, "W",
			            8, "X",
			            9, "Y"
			        );
			assertEquals(comps, bts.getStandardCompletions());
		}
	}
}
