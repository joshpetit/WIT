package dev.joshpetit.wit.core.base;
public abstract class InputInterpreter {
	private TypingSystem ts;

	public void setTypingSystem(TypingSystem ts) {
		this.ts = ts;
	}

	public void passInput(int key) {
		ts.input(key);
	}
}
