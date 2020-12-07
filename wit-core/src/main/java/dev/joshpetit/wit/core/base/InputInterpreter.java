package dev.joshpetit.wit.core.base;
public abstract class InputInterpreter {
	protected TypingSystem typingSystem;
	public InputInterpreter(TypingSystem ts) {
		this.typingSystem = typingSystem;
	}

	public void setTypingSystem(TypingSystem ts) {
		this.typingSystem = typingSystem;
	}

	public void passInput(int key) {
		typingSystem.input(key);
	}
}
