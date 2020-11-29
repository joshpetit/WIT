package dev.joshpetit.wit.core.interpret;

import dev.joshpetit.wit.core.model.*;
import java.util.Properties;

public class StringSystem extends TypingSystem {
	private StringBuilder text;

	public StringSystem(Properties config) {
		super(config);
		text = new StringBuilder();
	}

	public void appendCommand(AppendCommand com) {
		if (capslock) {
			this.text.append(com.getUpper());
		} else {
			this.text.append(com.getLower());
		}
	}

	public String getText() {
		return text.toString();
	}

	public void systemCommand(SystemCommand com) {
		if (com.getCommand() == Command.SYSTEM.CAPS_LOCK) {
			this.capslock = !this.capslock;
		}
	}

	public void deleteCommand(DeleteCommand com) {
		if (com.getCommand() == Command.DELETE.CHAR) {
			text.deleteCharAt(text.length() -1);
		}
	}

}
