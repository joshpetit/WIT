package dev.joshpetit.wit.core.model;
import dev.joshpetit.wit.core.interpret.TypingSystem;

public class DeleteCommand extends Command {
	private Command.DELETE delcmd;
	public DeleteCommand(Command.DELETE delcmd) {
		super(Command.TYPE.DELETE);
		this.delcmd = delcmd;
	}

	public void execute(TypingSystem system) {
		system.deleteCommand(this);
	}

	public Command.DELETE getCommand() {
		return this.delcmd;
	}
}
