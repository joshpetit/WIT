package dev.joshpetit.wit.core.base;
import java.util.HashMap;
import java.util.Map;

/**
 * Translates strings into integer inputs
 * to pass into the {@link dev.joshpetit.wit.core.base.TypingSystem}.
 */
public class StringInterpreter extends InputInterpreter {
	protected Map<String, Integer> map;
	private boolean mappingMode;

	/**
	 * Constructs the interpreter with the
	 * specified typing system and a hashmap
	 * to translate values.
	 */
	public StringInterpreter(TypingSystem ts) {
		super(ts);
		map = new HashMap<>();
	}

	/**
	 * Directely set the string to integer mappings for
	 * the map. If map is null nothing will be changed.
	 */
	public void setMappings(Map<String, Integer> map) {
		if (map == null) {
			return;
		} else {
			this.map = map;
		}
	}

	/**
	 * Take in a stringed input to be translated
	 * into an integer and passed into the {@link StringInterpreter#typingSystem}
	 */
	public void input(String input) {
		Integer res = map.get(input);
		if (res != null) {
			passInput(res);
		} 
	}

	/**
	 * Add a string value pair to the {@link StringInterpreter#typingSystem}
	 */
	public void addMapping(String input, int value) {
		map.put(input, value);
	}

	/**
	 * Clear the map used for translating strings to integers.
	 */
	public void resetMappings() {
		map.clear();
	}

	/**
	 * Retrieve the current {@link StringInterpreter#map}
	 * used to translate inputs.
	 */
	public Map<String, Integer> getMappings() {
		return this.map;
	}
}
