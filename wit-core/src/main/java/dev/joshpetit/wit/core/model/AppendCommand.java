package dev.joshpetit.wit.core.model;
import dev.joshpetit.wit.core.interpret.System;

public class AppendCommand extends Command {
	private String lower;
	private	String upper;
	public AppendCommand(String lower, String upper) {
		super(Command.TYPE.APPEND);
		this.lower = lower;
		this.upper = upper;
	}

	@Override
	public void execute(System system) {
		system.appendCommand(this);
	}

	public String getLower() {
		return this.lower;
	}
	
	public String getUpper() {
		return this.upper;
	}
}
