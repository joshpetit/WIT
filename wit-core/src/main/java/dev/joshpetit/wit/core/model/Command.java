package dev.joshpetit.wit.core.model;
import dev.joshpetit.wit.interpret.System

public abstract class Command {

	public static class TYPE {
		public static int SYSTEM = 0;
		public static int APPEND = 1;
		public static int DELETE = 2;
	}

	public static enum SYSTEM {
		CAPS_LOCK;
	}

	public static enum DELETE {
		CHAR, WORD
	}

	private int type;

	public abstract void execute(System system);

	public Command(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

}
