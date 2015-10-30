package project.hw6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class ReadFile {

	private String filename;
	private HashMap<String, Integer> wordMap;

	public ReadFile(String filename) {
		this.filename = filename;
		wordMap = new HashMap<String, Integer>();
	}

	private void processLine(String line) {

		String[] words = line.split(" ");
		for (int i = 0; i < words.length; i++) {

			if (!wordMap.containsKey(words[i].toLowerCase())) {
				wordMap.put(words[i].toLowerCase(), 1);
			} else {
				int value = wordMap.get(words[i].toLowerCase());
				wordMap.put(words[i].toLowerCase(), value + 1);
			}
		}

	}

	public void readFile() {

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line;

			// for each line

			while ((line = br.readLine()) != null) {

				processLine(line);
			}
		} catch (Exception x) {

			x.printStackTrace();
		} finally {

			if (fr != null) {

				try {
					br.close();
				} catch (Exception ignoreMe) {
				}

				try {

					fr.close();
				} catch (Exception ignoreMe) {
				}
			}
		}

	}

	public HashMap<String, Integer> getWordMap() {
		return this.wordMap;
	}
}