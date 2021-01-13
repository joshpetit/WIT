package dev.joshpetit.wit.core.base;
/**
 * These classes translate input of a certain
 * type into a {@link dev.joshpetit.wit.core.base.TypingSystem}.
 */
public abstract class InputInterpreter {
	/**
	 * This is the system in which the interpreter
	 * passes input.
	 */
	protected TypingSystem typingSystem;

	/**
	 * Constructs the InputInterpreter with
	 * a specified typing system to pass
	 * input into.
	 */
	public InputInterpreter(TypingSystem ts) {
		this.typingSystem = ts;
	}

	/**
	 * Set the {@link InputInterpreter#typingSystem}.
	 */
	public void setTypingSystem(TypingSystem ts) {
		this.typingSystem = typingSystem;
	}

	/**
	 * Passes the translated input into the
	 * {@link dev.joshpetit.wit.core.base.TypingSystem}.
	 * @param key The translated input passed to the {@link dev.joshpetit.wit.core.base.TypingSystem}.
	 */
	public void passInput(int key) {
		typingSystem.input(key);
	}
}
