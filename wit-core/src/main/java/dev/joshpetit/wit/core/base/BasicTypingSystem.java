package dev.joshpetit.wit.core.base;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import dev.joshpetit.wit.core.commands.*;

/**
 * A BasicTypingSystem is the most simple way to use
 * WIT as a keyboard. It interprets numbers passed in
 * and translates every two inputs into the corresponding
 * command.
 */
public class BasicTypingSystem extends TypingSystem<BasicCommandable> {
	/**
	 * The numbers inputted into the system, a maximum of two.
	 * Once inputs reaches its threshold of 2 it is sent to
	 * {@link TypingSystem#executeCommand(String)} to be constructed
	 * into a {@link dev.joshpetit.wit.core.commands.Command}.
	 */
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

	public boolean clear() {
		if (inputs.length() == 0) {
			return false;
		} else {
			inputs.setLength(0);
			return true;
		}
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

	/**
	 * Returns possible key combinations based on what
	 * has been currently passed to the system.
	 * Gives alphabetical values priority in maps.
	 */
	public Map<Integer, String> getStandardCompletions() {
		Map<Integer, String> map = new HashMap<>();
		String value;
		if (inputs.length() == 0) {
			for (int i = 0; i < 10; i++) {
				value = "";
				String command1 = config.getProperty("" + i + 5);
				String command2 = config.getProperty("" + i + 9);
				Command begC = command1 == null ? null : BasicTypingSystem.parseCommand(command1);
				Command begE = command2 == null ? null : BasicTypingSystem.parseCommand(command2);
				if (begE instanceof AppendCommand) {
					AppendCommand beg = (AppendCommand) begC;
					AppendCommand end = (AppendCommand) begE;
					if (!Character.isAlphabetic(beg.getLower().charAt(0))) {
						value = "SPACES";
					} else {
						command1 = context.nextUpper() ? beg.getUpper() : beg.getLower();
						command2 = context.nextUpper() ? end.getUpper() : end.getLower();
						value = command1 + "-" + command2;
					}
				} else if (begC instanceof DeleteCommand) {
					value = "DELETE";
				} else if (begC instanceof MessageCommand) {
					value = "SHIFTS";
				}
				map.put(i, value);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				String n = inputs.toString();
				String command = config.getProperty(n + i);
				value = "";
				Command comu = command != null ? BasicTypingSystem.parseCommand(command) : null;
				if (comu instanceof AppendCommand) {
					AppendCommand com = (AppendCommand) BasicTypingSystem.parseCommand(command);
					value = context.nextUpper() ? com.getUpper() : com.getLower();
					if (!Character.isAlphabetic(value.charAt(0))) {
						switch (value) {
						case " ":
							value = "SPACE";
							break;
						case "\t":
							value = "TAB";
							break;
						case "\n":
							value = "N.L";
							break;
						case "\r":
							value = "ENTER";
							break;
						}
					}
				} else if (comu instanceof DeleteCommand) {
					DeleteCommand.TYPE c = ( (DeleteCommand) comu).getType();
					value = c.toString();
				} else if (comu instanceof MessageCommand) {
					value = ( (MessageCommand) comu).getType().toString();
				}
				map.put(i, value);
			}
		}

		return map;
	}

	/**
	 * Returns a {@link dev.joshpetit.wit.core.commands.Command} based on
	 * the passed in string.
	 * @param command The command in string format that will be decoded
	 * to create a {@link dev.joshpetit.wit.core.commands.Command}.
	 */
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

	/**
	 * Returns the properties {@link BasicTypingSystem#parseCommand(String)}
	 * uses to to parse commands.
	 */
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
