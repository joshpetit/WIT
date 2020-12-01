package dev.joshpetit.wit.core.commands;

public class DeleteCommand extends Command {
	public DeleteCommand(DeleteCommand.TYPE t) {

	}
	public enum TYPE {
		CHAR, WORD
	}
}
