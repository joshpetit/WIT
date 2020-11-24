package dev.joshpetit.wit.core.base;

import java.util.Properties;
import dev.joshpetit.wit.core.model.*;

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
			if (type == Command.TYPE.APPEND) {
				return new AppendCommand("" + command.charAt(1), "" + command.charAt(2));
			} else {
				throw new IllegalArgumentException("Invalid Command Type");
			}
		}
	}

}
