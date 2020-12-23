package dev.joshpetit.wit.gui.launcher;

import javafx.scene.control.TextArea;
import dev.joshpetit.wit.core.commands.BasicCommandable;
import dev.joshpetit.wit.core.commands.*;

public class CommandableTextArea extends TextArea implements BasicCommandable{
	boolean capsLockOn;

	public CommandableTextArea() {
		super();
	}

	public CommandableTextArea(String text) {
		super(text);
	}

	public void typingAppend(AppendCommand c) {
		appendText( capsLockOn ? c.getUpper() : c.getLower());
    }

	public void typingDelete(DeleteCommand c) {
		switch(c.getType()) {
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
		switch(c.getType()) {
			case CAPS_LOCK:
				capsLockOn = !capsLockOn;
		}
	}

    @Override
    public void requestFocus() {

    }
}
