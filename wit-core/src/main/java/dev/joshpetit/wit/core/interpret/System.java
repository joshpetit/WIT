package dev.joshpetit.wit.core.base.interpret;
import java.util.Properties;
import dev.joshpetit.wit.core.base.Translator;
import dev.joshpetit.wit.core.model.*;

public abstract class System {
	boolean capslock;
	private Properties config;
	public class System(Properties config) {
		this.config = config;
	}

	public input(int k1, int k2) {
		Command c = Translator(k1, k2, this.config);
	}

	public boolean capsLockOn() {
		return capslock;
	}

}
