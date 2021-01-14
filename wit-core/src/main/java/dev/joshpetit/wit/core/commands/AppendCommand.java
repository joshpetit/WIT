package dev.joshpetit.wit.core.commands;

/**
 * A command that tells a
 * {@link dev.joshpetit.wit.core.commands.AppendCommandable} context
 * to append characters.
 */
public class AppendCommand extends Command {
	private String lower;
	private	String upper;

	/**
	 * Constructs an Append command with lower
	 * and uppercase values.
	 */
	public AppendCommand(String lower, String upper) {
		this.lower = lower;
		this.upper = upper;
	}

	/**
	 * Returns the lowercase
	 * representation of the AppendCommand
	 */
	public String getLower() {
		return this.lower;
	}

	/**
	 * Returns the uppercase
	 * representation of the AppendCommand
	 */
	public String getUpper() {
		return this.upper;
	}

}
