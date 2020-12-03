package dev.joshpetit.wit.core.commands;

public class MessageCommand extends Command {
	private MessageCommand.TYPE type;
	public enum TYPE {
		CAPS_LOCK
	}

	public MessageCommand(MessageCommand.TYPE t) {
		this.type = t;
	}

	public MessageCommand.TYPE getType() {
		return this.type;
	}

}
