package dev.joshpetit.wit.core.base;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * A Stream Interpreter can read data from a stream, translates
 * them into strings and then into integers to pass into
 * a {@link dev.joshpetit.wit.core.base.TypingSystem}.
 */
public class StreamInterpreter extends InputInterpreter {
	protected Map<String, Integer> map;
	private BufferedReader reader;
	private boolean read;
	private boolean newLine;

	public StreamInterpreter(TypingSystem ts) {
		super(ts);
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * Begins reading the stream to create
	 * the needed mappings. The first ten lines
	 * will be read and set to the value corresponding
	 * to the order they are sent.
	 */
	private void createMappings() {
		map = new HashMap<>();
		for(int i=0; i < 10; i++) {
		try {
			if (newLine) {
				map.put(reader.readLine(), i);
			} else {
				map.put("" + (char) reader.read(), i);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		}
	}

	/**
	 * Sets the input stream that the interpreter
	 * uses to read data from.
	 */
	public void setInputStream(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
	}

	/**
	 * Sets whether the marker between an input
	 * is the new line or if it is the read character.
	 * If set to true every time a new line character
	 * is encountered that piece of data will be read
	 * as an input, otherwise every character sent through
	 * the stream will be interpreted as an input.
	 */
	public void newLineDenotation(boolean b) {
		this.newLine = b;
	}

	/**
	 * Returns the mappings currently used to
	 * translate inputs.
	 */
	public Map<String, Integer> getMappings() {
		return this.map;
	}

	/**
	 * Begins the process of creating mappings for
	 * the interpreter. The first 10 inputs will
	 * be read and create the corresponding value.
	 */
	public void setMappings() {
		createMappings();
	}

	/**
	 * Directly add and create the inerpreter mappings.
	 * @param map If null nothing will change.
	 */
	public void setMappings(Map<String, Integer> map) {
		if (map == null) {
			return;
		} else {
			this.map = map;
		}
	}

	/**
	 * Begin reading input from the stream
	 * to pass into the {@link StringInterpreter#typingSystem}.
	 * This method will block the current thread until
	 * the stream is empty.
	 */
	public void start() {
		String s = "";
		int c = '\u0000';
		int num = -1;
		read = true;
		try {
			do{
				num = map.getOrDefault(s, -1);
				if (num != -1) {
					typingSystem.input(num);
				}
				if (newLine) {
					s = reader.readLine();
				} else {
					c = reader.read();
					s = c == -1 ? null : "" + (char) c;
				}
			} while (s != null && read) ;
		}
		 catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the reading that was commenced
	 * by {@link StreamInterpreter#start()}.
	 */
	public void stop() {
		read = false;
	}
}
