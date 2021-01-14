package dev.joshpetit.wit.core.commands;

/**
 * A command that tells a
 * {@link dev.joshpetit.wit.core.commands.MessageCommandable} context
 * messages. These messages are specified within the {@link TYPE} enum.
 */
public class MessageCommand extends Command {
	private MessageCommand.TYPE type;

	/**
	 * The message the message
	 * command is sending.
	 */
	public enum TYPE {
		/**
		 * Equivalent of pressing Caps Lock on
		 * a keyboard.
		 */
		CAPS_LOCK,

		/**
		 * Equivalent of pressing Shift on
		 * a keyboard.
		 */
		SHIFT
	}

	/**
	 * Constructs the delete command with
	 * a type based on the
	 * {@link TYPE} enum
	 */
	public MessageCommand(MessageCommand.TYPE t) {
		this.type = t;
	}

	/**
	 * Returns the kind of message command
	 * this object was constructed to be.
	 */
	public MessageCommand.TYPE getType() {
		return this.type;
	}

}
