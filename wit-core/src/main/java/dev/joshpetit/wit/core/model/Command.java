package dev.joshpetit.wit.core.model;

public abstract class Command {
	public static class TYPE {
		public static int SYSTEM = 0;
		public static int APPEND = 1;
		public static int DELETE = 2;
	}
	private int type;

	public Command(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

}
