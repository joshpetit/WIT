package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;

public abstract class TypingSystem<T extends Commandable> {
	protected T context;
	protected Properties config;

	public TypingSystem(Properties config, T context) {
		this.config = config;
		this.context = context;
	}

	public abstract void input(int key);

	public abstract void executeCommand(String key);

	public void setContext( T context) {
		this.context = context;
	}

	public T getContext() {
		return this.context;
	}


}
