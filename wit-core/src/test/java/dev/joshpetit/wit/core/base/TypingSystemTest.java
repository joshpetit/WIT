package dev.joshpetit.wit.core.base;

import dev.joshpetit.wit.core.commands.*;

import java.util.Properties;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;


class TypingSystemTest {

	@Nested
	@DisplayName("Tests for String Context")
	class BasicTypingSystemTest {

		@Test
		void testMethodCalls() {
			boolean[] called = new boolean[3];
			BasicCommandable context = new BasicCommandable() {
				public void typingMessage(MessageCommand c) {called[0] = true;}
				public void typingDelete(DeleteCommand c) {called[1] = true;}
				public void typingAppend(AppendCommand c) {called[2] = true;}
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
	}
}