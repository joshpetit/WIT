package dev.joshpetit.wit.core.base;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
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

	/*
	 * Returns possible key combinations based on what
	 * is passed. Focuses on alphabetical values.
	 * May have to modify this to make more abstract.
	 */
	public Map<Integer, String> getStandardCompletions() {
		Map<Integer, String> map = new HashMap<>();
		if (inputs.length() == 0) {
			for (int i = 0; i < 10; i++) {
				if ( i < 5 ) {
					String command1 = config.getProperty("" + i + 5);
					String command2 = config.getProperty("" + i + 9);
					AppendCommand beg = (AppendCommand) BasicTypingSystem.parseCommand(command1);
					AppendCommand end = (AppendCommand) BasicTypingSystem.parseCommand(command2);
					command1 = context.nextUpper() ? beg.getUpper() : beg.getLower();
					command2 = context.nextUpper() ? end.getUpper() : end.getLower();
					map.put(i, command1 + "-" + command2);
				} else {
					map.put(i, "");
				}
			}
		} else {
			for (int i = 0; i < 10; i++) {
				String n = inputs.toString();
				String command = config.getProperty(n + i);
				Command comu = command != null ? BasicTypingSystem.parseCommand(command) : null;
				if (comu instanceof AppendCommand) {
					AppendCommand com = (AppendCommand) BasicTypingSystem.parseCommand(command);
					command = context.nextUpper() ? com.getUpper() : com.getLower();
					map.put(i, command);
				} else {
					map.put(i, "");
				}
			}
		}

		return map;
	}

	public static Command parseCommand(String command) {
		int ctype;
		switch (command.charAt(0)) {
		case '0':
			ctype = command.charAt(1) - 48; // (int) '0' = 48
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

	public static Properties getDefaultProperties() {
		try {
			Properties config = new Properties();
			config.load(InputInterpreter.class.getResourceAsStream("defaultConfig.properties"));
			return config;
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
