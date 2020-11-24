package dev.joshpetit.wit.core.model;

public class SystemCommand extends Command {
	private Command.SYSTEM syscmd;
	public SystemCommand(Command.SYSTEM syscmd) {
		super(Command.TYPE.SYSTEM);
		this.syscmd = syscmd;
	}

	/*
	 * Corresponds to [Command.SYSTEM.COMMAND_ENUMERATION]
	 */
	public Command.SYSTEM getCommand() {
		return this.syscmd;
	}
}
