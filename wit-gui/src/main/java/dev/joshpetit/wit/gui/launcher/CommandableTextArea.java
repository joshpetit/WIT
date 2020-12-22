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
		// commitValue();
    }
	public void typingDelete(DeleteCommand c) {

	}
	public void typingMessage(MessageCommand c) {

	}
}
