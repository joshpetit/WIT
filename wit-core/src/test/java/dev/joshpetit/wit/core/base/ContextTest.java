package dev.joshpetit.wit.core.base;

import static org.junit.jupiter.api.Assertions.*;
import dev.joshpetit.wit.core.commands.*;

import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;

class ContextsTest {

	@Nested
	@DisplayName("Tests for String Context")
	class StringContextCases {
		AppendCommand appendLetter = new AppendCommand("a", "A");
		// Possibly configure hot words to be appending with preceeding space
		AppendCommand appendWord = new AppendCommand("abc", "Abc"); 
		AppendCommand appendSpace = new AppendCommand(" ", " ");
		DeleteCommand deleteChar = new DeleteCommand(DeleteCommand.TYPE.CHAR);
		DeleteCommand deleteWord = new DeleteCommand(DeleteCommand.TYPE.WORD);

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

		@Test
		void testTypingDelete() {
			StringContext s = new StringContext();
			s.typingAppend(appendLetter);
			s.typingAppend(appendLetter);
			s.typingAppend(appendLetter);

			s.typingDelete(deleteChar);
			assertEquals(s.getText(), "aa");
			
			s.typingAppend(appendSpace);
			s.typingAppend(appendWord);
			s.typingDelete(deleteWord);
			assertEquals(s.getText(), "aa");
		}
	}
}
