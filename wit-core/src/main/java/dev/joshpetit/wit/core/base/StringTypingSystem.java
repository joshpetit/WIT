package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;

public class StringTypingSystem extends TypingSystem<BasicCommandable> {
	
	public StringTypingSystem(Properties config, BasicCommandable context) {
		super(config, context);
	}

	public void input(int key) {
		this.inputs.append(key);
		if (! (inputs.length() > 1)) return;
		executeCommand(inputs.toString());
		inputs.setLength(0);
	}

	public void executeCommand(String key) {
		String command = config.getProperty(key);
		if (command == null) return;
		Command parsed = TypingSystem.parseCommand(command);
		if (parsed instanceof MessageCommand) {
			this.context.typingMessage((MessageCommand) parsed);
		} else if (parsed instanceof DeleteCommand) {
			this.context.typingDelete((DeleteCommand) parsed);
		} else if (parsed instanceof AppendCommand) {
			this.context.typingAppend((AppendCommand) parsed);
		}
	}
}
