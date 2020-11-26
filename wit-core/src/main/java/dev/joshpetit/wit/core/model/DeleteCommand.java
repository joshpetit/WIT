package dev.joshpetit.wit.core.model;
import dev.joshpetit.wit.core.interpret.System;

public class DeleteCommand extends Command {
	private Command.DELETE delcmd;
	public DeleteCommand(Command.DELETE delcmd) {
		super(Command.TYPE.DELETE);
		this.delcmd = delcmd;
	}

	@Override
	public void execute(System system) {
		system.deleteCommand(this);
	}

	public Command.DELETE getCommand() {
		return this.delcmd;
	}
}
