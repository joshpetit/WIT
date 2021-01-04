package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;


public class StringContext implements BasicCommandable {
	protected StringBuilder text;
	boolean capsLockOn;
	boolean nextUpper;

	public StringContext() {
		this("");
	}

	public StringContext(String current) {
		text = new StringBuilder(current);
	}

	public void typingAppend(AppendCommand c) {
		text.append( nextUpper || capsLockOn ? c.getUpper() : c.getLower());
		if (nextUpper && !capsLockOn) {
			nextUpper = false;
		}
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
		switch (c.getType()) {
		case CAPS_LOCK:
			capsLockOn = !capsLockOn;
			nextUpper = capsLockOn;
			break;
		case SHIFT:
			nextUpper = !nextUpper;
			break;
		}
	}

	public boolean capsLockOn() {
		return this.capsLockOn;
	}

	public boolean nextUpper() {
		return this.nextUpper;
	}

	public String getText() {
		return text.toString();
	}
}
