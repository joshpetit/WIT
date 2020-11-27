package dev.joshpetit.wit.core.model;
import dev.joshpetit.wit.core.interpret.TypingSystem;


public class SystemCommand extends Command {
	private Command.SYSTEM syscmd;
	public SystemCommand(Command.SYSTEM syscmd) {
		super(Command.TYPE.SYSTEM);
		this.syscmd = syscmd;
	}

	public void execute(TypingSystem system) {
		system.systemCommand(this);
	}

	/*
	 * Corresponds to [Command.SYSTEM.COMMAND_ENUMERATION]
	 */
	public Command.SYSTEM getCommand() {
		return this.syscmd;
	}
}
