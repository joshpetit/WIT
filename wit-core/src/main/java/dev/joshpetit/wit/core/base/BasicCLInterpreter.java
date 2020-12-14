package dev.joshpetit.wit.core.base;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class BasicCLInterpreter extends InputInterpreter {
	protected Map<String, Integer> map;
	private BufferedReader reader;
	private boolean read;
	private boolean newLine;

	public BasicCLInterpreter(TypingSystem ts) {
		super(ts);
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

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

	public void setInputStream(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
	}

	public void newLineDenotation(boolean b) {
		this.newLine = b;
	}

	public Map<String, Integer> getMappings() {
		return this.map;
	}

	public void setMappings() {
		createMappings();
	}

	public void setMappings(Map<String, Integer> map) {
		if (map == null) {
			return;
		} else {
			this.map = map;
		}
	}

	public void start() {
		String s;
		read = true;
		try {
			if (newLine) {
				s = reader.readLine();
			} else { 
				s = "" + (char) reader.read();
			}
			while(read && (s = reader.readLine()) != null) {
				System.out.println(s);
			} 		
		}
		 catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		read = false;
	}
}
