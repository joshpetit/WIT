package dev.joshpetit.wit.core.base;
import java.util.Properties;
import java.util.List;
import dev.joshpetit.wit.core.commands.*;

/**
 * The typing system is the middleway to 
 * communicate with a context.
 *
 * The typing system holds a context that implements
 * some level of 
 * {@link dev.joshpetit.wit.core.commands.Commandable}
 *
 * typically receives inputs from 
 * {@link dev.joshpetit.wit.core.base.InputInterpreter}
 * that it translates into commands to {@link TypingSystem#context}
 */
public abstract class TypingSystem<T extends Commandable> {

	/**
	 * The object in which the typing system sends 
	 * commands to.
	 */
	protected T context;

	/**
	 * The configuration that the typing
	 * system uses to translate inputs.
	 */
	protected Properties config;

	/**
	 * Constructs a TypingSystem appropriately.
	 * @param config The configuration mappings the system translates.
	 * @param context the context that that the system
	 * inputs comamnds to.
	 */
	public TypingSystem(Properties config, T context) {
		this.config = config;
		this.context = context;
	}

	/**
	 * Sends a numbered input to the system that it can
	 * use to translate with other inputs.
	 *
	 * Inputing is usually done by the 
	 * {@link dev.joshpetit.wit.core.base.InputInterpreter}.
	 * Once the system determines that it has the
	 * necessary inputs to make a translation it will do
	 * so with its {@link TypingSystem#config}.
	 */
	public abstract void input(int key);

	/**
	 * Retrieves the configuration specified
	 * by the key and sends the corresponding
	 * command to the {@link TypingSystem#context}.
	 * @param key the command that maps to
	 * a key within the {@link TypingSystem#config}.
	 */
	public abstract void executeCommand(String key);

	/**
	 * Changes the current {@link TypingSystem#context}.
	 */
	public void setContext( T context) {
		this.context = context;
	}

	/**
	 * Clears the current inputs within the TypingSystem
	 * and returns true if any values were cleared.
	 */
	public abstract boolean clear();

	/**
	 * Returns the current {@link TypingSystem#context} in which
	 * this system is inputting into.
	 */
	public T getContext() {
		return this.context;
	}


}
