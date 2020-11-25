package dev.joshpetit.wit.core.model;

public class DeleteCommand extends Command {
	private Command.DELETE delcmd;
	public DeleteCommand(Command.DELETE delcmd) {
		super(Command.TYPE.DELETE);
		this.delcmd = delcmd;
	}

	@Override
	public void execute(System system) {

	}

	public Command.DELETE getCommand() {
		return this.delcmd;
	}
}
