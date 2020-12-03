package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;

public class BasicTypingSystem extends TypingSystem<BasicCommandable> {
	protected StringBuilder inputs;
	
	public BasicTypingSystem(Properties config, BasicCommandable context) {
		super(config, context);
		inputs = new StringBuilder();
	}

	public void input(int key) {
		inputs.append(key);
		if (! (inputs.length() > 1)) return;
		executeCommand(inputs.toString());
		inputs.setLength(0);
	}

	public void executeCommand(String key) {
		String command = config.getProperty(key);
		if (command == null) return;
		Command parsed = BasicTypingSystem.parseCommand(command);
		if (parsed instanceof MessageCommand) {
			this.context.typingMessage((MessageCommand) parsed);
		} else if (parsed instanceof DeleteCommand) {
			this.context.typingDelete((DeleteCommand) parsed);
		} else if (parsed instanceof AppendCommand) {
			this.context.typingAppend((AppendCommand) parsed);
		}
	}

	public static Command parseCommand(String command) {
		// First two if statements can be grouped as n < 2
		if(command.charAt(0) == '0') {
			int ctype = command.charAt(1) - 48; // 0 = 48
			MessageCommand.TYPE type = MessageCommand.TYPE.values()[ctype];
			return new MessageCommand(type);
		} else if (command.charAt(0) == '1') {
			int ctype = command.charAt(1) - 48;
			DeleteCommand.TYPE type = DeleteCommand.TYPE.values()[ctype];
			return new DeleteCommand(type);
		} else if (command.charAt(0) == '2') {
			// Potential complex analysis, i.e lengths of upper + lower specified.
			return new AppendCommand("" + command.charAt(1), "" + command.charAt(2));
		}

		return null;
	}
}
