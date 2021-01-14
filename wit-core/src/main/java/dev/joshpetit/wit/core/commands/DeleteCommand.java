package dev.joshpetit.wit.core.commands;

/**
 * A command that tells a
 * {@link dev.joshpetit.wit.core.commands.DeleteCommandable} context
 * to delete characters.
 */
public class DeleteCommand extends Command {
	private DeleteCommand.TYPE type;

	/**
	 * Constructs the delete command with
	 * a type based on the
	 * {@link TYPE} enum
	 */
	public DeleteCommand(DeleteCommand.TYPE t) {
		this.type = t;
	}

	/**
	 * The type of execution the delete
	 * command specifies.
	 */
	public enum TYPE {
		/**
		 * Deletes a single character.
		 */
		CHAR,

		/**
		 * Deletes an entire word.
		 */
		WORD
	}

	/**
	 * Returns the kind of delete command
	 * this object was constructed to be.
	 */
	public DeleteCommand.TYPE getType() {
		return type;
	}
}
