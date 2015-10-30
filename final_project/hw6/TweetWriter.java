package project.hw6;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author alikozlu
 * 
 *         Class is responsible for creating a JSON file including all JSON
 *         Tweet Objects
 */

public class TweetWriter {

	private PrintStream writer;
	private String filename;
	private File file;
	private boolean writer_created;

	public TweetWriter(String filename, File file, PrintStream writer) {
		this.filename = filename;
		this.file = file;
		this.writer = writer;
		writer_created = false;
	}

	public void storeJSON(String rawJSON) throws IOException {

		file = new File(filename);
		boolean file_existed_before = true;

		if (!file.exists()) {

			try {

				file.createNewFile();
				writer = new PrintStream(file);
				file_existed_before = false;

				if (file_existed_before) {

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!writer_created) {
			writer = new PrintStream(file);
			writer_created = true;
		}

		try {

			writer.println(rawJSON);
			writer.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PrintStream getWriter() {
		return this.writer;
	}
}
