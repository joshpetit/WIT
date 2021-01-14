package dev.joshpetit.wit.core.commands;

/**
 * Defines classes that are capable of receiving the
 * {@link dev.joshpetit.wit.core.commands.DeleteCommand}.
 */
public interface DeleteCommandable extends Commandable {

	/**
	 * Handles a passed in command to delete
	 * a number of characters.
	 */
	public void typingDelete(DeleteCommand c);
}
