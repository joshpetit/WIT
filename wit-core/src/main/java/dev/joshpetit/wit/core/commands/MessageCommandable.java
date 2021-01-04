package dev.joshpetit.wit.core.commands;
public interface MessageCommandable extends Commandable {
	public void typingMessage(MessageCommand c);
	public boolean nextUpper();
}
