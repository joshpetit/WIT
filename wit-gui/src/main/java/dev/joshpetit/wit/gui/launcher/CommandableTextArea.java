package dev.joshpetit.wit.gui.launcher;

import javafx.scene.control.TextArea;
import dev.joshpetit.wit.core.commands.BasicCommandable;
import dev.joshpetit.wit.core.commands.*;

public class CommandableTextArea extends TextArea implements BasicCommandable {
	boolean capsLockOn;
	boolean nextUpper;

	public CommandableTextArea() {
		super();
	}

	public CommandableTextArea(String text) {
		super(text);
	}

	public boolean capsLockOn() {
		return this.capsLockOn;
	}

	public boolean nextUpper() {
		return this.nextUpper;
	}


	public void typingAppend(AppendCommand c) {
		appendText( nextUpper ? c.getUpper() : c.getLower());
		if (nextUpper && !capsLockOn) {
			nextUpper = false;
		}
	}

	public void typingDelete(DeleteCommand c) {
		switch (c.getType()) {
		case CHAR:
			deletePreviousChar();
			break;
		case WORD:
			previousWord();
			selectNextWord();
			replaceSelection("");
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

	@Override
	public void requestFocus() {

	}
}
