package dev.joshpetit.wit.core.commands;

/**
 * Defines classes that are capable of receiving the
 * {@link dev.joshpetit.wit.core.commands.AppendCommand}.
 */
public interface AppendCommandable extends Commandable {

	/**
	 * Handle a passed in command to append
	 * a string.
	 */
	public void typingAppend(AppendCommand c);
}
