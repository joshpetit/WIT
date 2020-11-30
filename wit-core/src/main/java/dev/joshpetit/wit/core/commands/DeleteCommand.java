package dev.joshpetit.wit.core.commands;

public class DeleteCommand extends Command {
	enum TYPE {
		CHAR, WORD
	}
	private Command.DELETE delcmd;
	public DeleteCommand(Command.DELETE delcmd) {
		this.delcmd = delcmd;
	}

	public Command.DELETE getCommand() {
		return this.delcmd;
	}
}
