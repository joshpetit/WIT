package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;

public abstract class TypingSystem {
	protected StringBuilder inputs;
	protected Commandable context;
	protected Properties config;

	public TypingSystem(Properties config, Commandable context) {
		this.config = config;
		this.context = context;
	}

	public abstract void input(int key);

	public abstract void executeCommand(String key);

	public void setContext(Commandable context) {
		this.context = context;
	}

	public Commandable getContext() {
		return this.context;
	}

	public static Command parseCommand(String command) {
		// First two if statements can be grouped as n < 2
		if(command.charAt(0) == 0) {
			int ctype = command.charAt(1) - 48; // 0 = 48
			MessageCommand.TYPE type = MessageCommand.TYPE.values()[ctype];
			return new MessageCommand(type);
		} else if (command.charAt(0) == 1) {
			int ctype = command.charAt(1) - 48;
			DeleteCommand.TYPE type = DeleteCommand.TYPE.values()[ctype];
			return new DeleteCommand(type);
		} else if (command.charAt(0) == 2) {
			// Potential complex analysis, i.e lengths of upper + lower specified.
			return new AppendCommand("" + command.charAt(1), "" + command.charAt(2));
		}

		return null;
	}

}
