package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;

public abstract class TypingSystem {
	private List<Integer> inputs;
	private Commandable context;
	private Properties config;

	public TypingSystem(Properties config) {
		this.config = config;
	}

	public void input(int key) {
		inputs.add(key);
	}

	public abstract void parseCommand(String key);

	public void setContext(Commandable context) {
		this.context = context;
	}

}
