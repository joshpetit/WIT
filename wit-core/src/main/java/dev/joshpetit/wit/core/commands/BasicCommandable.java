package dev.joshpetit.wit.core.commands;

public interface BasicCommandable extends AppendCommandable, MessageCommandable, DeleteCommandable{
	public void typingAppend(AppendCommand c);

	public void typingDelete(DeleteCommand c);

	public void typingMessage(MessageCommand c);
	public boolean nextUpper();
}
