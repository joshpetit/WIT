package dev.joshpetit.wit.core.commands;

/**
 * Defines classes that are capable of receiving the
 * {@link dev.joshpetit.wit.core.commands.MessageCommand}.
 */
public interface MessageCommandable extends Commandable {

	/**  
	 * Handles a passed in message command.
	 */
	public void typingMessage(MessageCommand c);
}
