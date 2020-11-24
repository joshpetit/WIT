package dev.joshpetit.wit.core.base;

import java.util.Properties;
import dev.joshpetit.wit.core.model.Command;

public class Translator {
	private static Properties config;

	public static void setConfig(Properties config) {
		Translator.config = config;
	}

	public static Command translate(int k1, int k2) {
		String key = "" + k1 + k2;
		if (!Translator.config.containsKey(key)) {
			throw new 
				IllegalArgumentException("Key combo does not have an associated value");
		} else {
			String command = config.getProperty(key);
			int type = command.charAt(0) - 48;
			if (type == Command.SYSTEM) {
				return new Command(Command.SYSTEM, command.substring(1));
			} else if (type == Command.APPEND) {
				return new Command(Command.APPEND, command.substring(1));
			} else if (type == Command.DELETE) {
				return new Command(Command.DELETE);
			} else {
				throw new IllegalArgumentException("Invalid Command Type");
			}
		}
	}

}
