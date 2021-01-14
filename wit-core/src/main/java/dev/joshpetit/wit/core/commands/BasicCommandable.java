package dev.joshpetit.wit.core.commands;

/**
 * A grouping interface that defines
 * classes capable of doing multiple
 * {@link dev.joshpetit.wit.core.commands.Commandable} interfaces.
 */
public interface BasicCommandable extends AppendCommandable, MessageCommandable, DeleteCommandable{
	public void typingAppend(AppendCommand c);
	public void typingDelete(DeleteCommand c);
	public void typingMessage(MessageCommand c);
	/**
	 * Specifies whether the next appended
	 * character will be upper case.
	 */
	public boolean nextUpper();
}
