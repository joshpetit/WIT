package dev.joshpetit.wit.core.interpret;

import dev.joshpetit.wit.core.model.*;
import java.util.Properties;

public class StringSystem extends TypingSystem {

	public StringSystem(Properties config) {
		super(config);
	}

	public void appendCommand(AppendCommand com) {
		System.out.println("About to Append something!");
	}

	public void systemCommand(SystemCommand com) {
		System.out.println("About to System something!");
	}

	public void deleteCommand(DeleteCommand com) {
		System.out.println("About to Delete something!");
	}

}
