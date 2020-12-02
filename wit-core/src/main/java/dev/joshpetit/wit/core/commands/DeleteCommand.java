package dev.joshpetit.wit.core.commands;

public class DeleteCommand extends Command {
	private DeleteCommand.TYPE type;
	public DeleteCommand(DeleteCommand.TYPE t) {
		this.type = t;
	}
	public enum TYPE {
		CHAR, WORD
	}

	public DeleteCommand.TYPE getType() {
		return type;
	}
}
