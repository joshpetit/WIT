package dev.joshpetit.wit.core.base;

import java.util.Properties;
import dev.joshpetit.wit.core.model.*;

public class Translator {

	public static Command translate(int k1, int k2, Properties config) {
		String key = "" + k1 + k2;
		if (!config.containsKey(key)) {
			throw new 
				IllegalArgumentException("Key combo does not have an associated value");
		} else {
			String command = config.getProperty(key);
			int type = command.charAt(0) - 48;
			if (type == Command.TYPE.APPEND) {
				return new AppendCommand("" + command.charAt(1), "" + command.charAt(2));
			} else if (type == Command.TYPE.SYSTEM) {
				int ctype = command.charAt(1) - 48;
				return new SystemCommand(Command.SYSTEM.values()[ctype]);
			} else if(type == Command.TYPE.DELETE){
				int ctype = command.charAt(1) - 48;
				return new DeleteCommand(Command.DELETE.values()[ctype]);
			} else {
				throw new IllegalArgumentException("Invalid Command Type");
			}
		}
	}

}
