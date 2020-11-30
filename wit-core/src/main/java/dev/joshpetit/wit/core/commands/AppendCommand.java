package dev.joshpetit.wit.core.commands;

public class AppendCommand extends Command {
	private String lower;
	private	String upper;
	public AppendCommand(String lower, String upper) {
		this.lower = lower;
		this.upper = upper;
	}

	public String getLower() {
		return this.lower;
	}

	public String getUpper() {
		return this.upper;
	}

}
