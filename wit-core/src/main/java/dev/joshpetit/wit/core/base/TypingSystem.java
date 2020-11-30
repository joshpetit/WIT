package dev.joshpetit.wit.core.base;
import java.util.Properties;
import dev.joshpetit.wit.core.commands.*;

public class TypingSystem {
	private List<Integer> inputs;
	private Commandable context;

	public void input(int key) {
		inputs.add(key);
	}

	public void parseCommand(String key) {

	}

	public void setContext(Commandable context) {
		this.context = context;
	}

}
