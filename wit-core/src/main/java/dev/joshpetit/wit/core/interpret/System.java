package dev.joshpetit.wit.core.interpret;
import java.util.Properties;
import dev.joshpetit.wit.core.base.Translator;
import dev.joshpetit.wit.core.model.*;

public abstract class System {
	boolean capslock;
	private Properties config;
	public System(Properties config) {
		this.config = config;
	}

	public void input(int k1, int k2) {
		Command c = Translator.translate(k1, k2, this.config);
		c.execute(this);
	}

	public boolean capsLockOn() {
		return capslock;
	}

	public abstract void appendCommand(AppendCommand com);
	public abstract void systemCommand(SystemCommand com);
	public abstract void deleteCommand(DeleteCommand com);

}
