package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;


public class StringContext implements BasicCommandable {
	protected StringBuilder text;
	boolean capsLockOn;

	public StringContext() {
		this("");
	}

	public StringContext(String current) {
		text = new StringBuilder(current);
	}

	public void typingAppend(AppendCommand c) {
		text.append( capsLockOn ? c.getUpper() : c.getLower());
	}

	public void typingDelete(DeleteCommand c) {
		switch (c.getType()) {
		case CHAR:
			text.deleteCharAt(text.length() - 1);
			break;
		case WORD:
			int lastSpace = text.lastIndexOf(" ");
			text.delete(lastSpace, text.length());
			break;
		}
	}

	public void typingMessage(MessageCommand c) {

	}

	public String getText() {
		return text.toString();
	}
}
