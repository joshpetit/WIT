package dev.joshpetit.wit.core.model;

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

	}

	public String getLower() {
		return this.lower;
	}
	
	public String getUpper() {
		return this.upper;
	}
}
