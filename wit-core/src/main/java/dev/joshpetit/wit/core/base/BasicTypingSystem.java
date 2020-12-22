package dev.joshpetit.wit.core.base;
import java.io.IOException;

import java.util.List;
import java.util.Properties;

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
		int ctype;
		switch(command.charAt(0)) {
			case '0':
				ctype = command.charAt(1) - 48; // 0 = 48
				MessageCommand.TYPE mtype = MessageCommand.TYPE.values()[ctype];
				return new MessageCommand(mtype);
			case '1':
				ctype = command.charAt(1) - 48;
				DeleteCommand.TYPE dtype = DeleteCommand.TYPE.values()[ctype];
				return new DeleteCommand(dtype);
			case '2':
				return new AppendCommand("" + command.charAt(1), "" + command.charAt(2));
		}
		return null;
	}

	public static Properties getDefaultProperties() throws IOException {
		Properties config = new Properties();
		config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));
		return config;
	}
}
