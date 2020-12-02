package dev.joshpetit.wit.core.base;

import static org.junit.jupiter.api.Assertions.*;
import dev.joshpetit.wit.core.commands.*;

import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;

class MainTest {

	@Nested
	@DisplayName("Tests for String Context")
	class StringContextCases {
		@Test
		void testTypingAppend() {
			StringContext s = new StringContext();
			AppendCommand c = new AppendCommand("a", "A");
			s.typingAppend(c);
			s.typingAppend(c);
			s.typingAppend(c);

			String res = s.getText();
			assertEquals(res, "aaa");
		}
	}
}
