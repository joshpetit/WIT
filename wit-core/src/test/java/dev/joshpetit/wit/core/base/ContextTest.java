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
		MessageCommand capsLock = new MessageCommand(MessageCommand.TYPE.CAPS_LOCK);
		MessageCommand shift = new MessageCommand(MessageCommand.TYPE.SHIFT);

		@Test
		void testTypingAppend() {
			StringContext s = new StringContext();
			AppendCommand c = new AppendCommand("a", "A");
			s.typingAppend(c);
			s.typingAppend(c);
			s.typingAppend(c);
			assertEquals(s.getText(), "aaa");

			s.typingAppend(appendSpace);
			s.typingAppend(appendWord);
			assertEquals(s.getText(), "aaa abc");
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

		@Test
		void testTypingMessage() {
			StringContext s = new StringContext();
			s.typingMessage(capsLock);
			assertTrue(s.capsLockOn());
			assertTrue(s.nextUpper());

			s.typingAppend(appendWord);
			assertEquals(s.getText(), "Abc");

			s.typingAppend(appendLetter);
			assertEquals(s.getText(), "AbcA");

			s.typingAppend(appendSpace);
			assertEquals(s.getText(), "AbcA ");

			s.typingMessage(capsLock);
			assertFalse(s.capsLockOn());
			assertFalse(s.nextUpper());

			s.typingAppend(appendLetter);
			assertEquals(s.getText(), "AbcA a");

			s.typingMessage(shift);
			assertTrue(s.nextUpper());

			s.typingAppend(appendLetter);
			assertFalse(s.nextUpper());
			assertEquals(s.getText(), "AbcA aA");

			s.typingAppend(appendLetter);
			assertEquals(s.getText(), "AbcA aAa");
		}
	}
}
