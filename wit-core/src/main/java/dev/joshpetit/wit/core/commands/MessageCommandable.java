package dev.joshpetit.wit.core.commands;
interface MessageCommandable extends Commandable {
	public void typingMessage(MessageCommand c);
}
