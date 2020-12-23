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
		if (c.getType() == DeleteCommand.TYPE.CHAR) {
			System.out.println("delete");
			deletePreviousChar();
		} else {
			System.out.println("word del");
			previousWord();
			selectNextWord();
			replaceSelection("");
		}
	}

    @Override
    public void requestFocus() {

    }

	public void typingMessage(MessageCommand c) {

	}
}
