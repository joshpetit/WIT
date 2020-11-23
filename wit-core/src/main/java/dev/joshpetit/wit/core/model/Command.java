package dev.joshpetit.wit.core.model;

public class Command {
	public static int SYSTEM = 0;
	public static int APPEND = 1;
	public static int DELETE = 2;
	private int type;
	private String payload;

	public Command(int type, String payload) {
		this.type = type;
		this.payload = payload;
	}

	public int getType() {
		return this.type;
	}

	public String getPayload() {
		return this.payload;
	}
}
