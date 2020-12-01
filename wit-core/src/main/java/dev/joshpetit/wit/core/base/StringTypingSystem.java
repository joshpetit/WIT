package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;

public class StringTypingSystem extends TypingSystem {
	
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
	}
}
