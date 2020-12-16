package dev.joshpetit.wit.core.base;
import java.util.HashMap;
import java.util.Map;

public class StringInterpreter extends InputInterpreter {
	protected Map<String, Integer> map;
	private boolean mappingMode;

	public StringInterpreter(TypingSystem ts) {
		super(ts);
		map = new HashMap<>();
	}

	public void setTypingSystem(TypingSystem ts) {
		this.typingSystem = typingSystem;
	}

	public void setMappings(Map<String, Integer> map) {
		if (map == null) {
			return;
		} else {
			this.map = map;
		}
	}

	public void input(String input) {
		Integer res = map.get(input);
		if (res != null) {
			passInput(res);
		} 
	}

	public void addMapping(String input, int value) {
		map.put(input, value);
	}

	public Map<String, Integer> getMappings() {
		return this.map;
	}
}
