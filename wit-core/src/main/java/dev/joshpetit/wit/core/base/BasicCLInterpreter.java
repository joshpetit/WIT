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

	public BasicCLInterpreter(TypingSystem ts) {
		super(ts);
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	private void createMappings() {
		map = new HashMap<>();
		for(int i=0; i < 10; i++) {
		try {
			map.put(reader.readLine(), i);
			System.out.println(map);
		} catch(IOException e) {
			e.printStackTrace();
		}
		}
	}

	public void setInputStream(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
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
}
