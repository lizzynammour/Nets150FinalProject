package project.hw6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Logger implements Observer {

	/**
	 * Logger is designed for Singleton pattern, lazy implementation It also
	 * extends Observer and overrides update method as part of Observer Pattern
	 * 
	 * @author Ali kozlu
	 */

	private File file;
	private static Logger logfile;
	private FileWriter writer;

	private Logger() {
	}

	public static Logger getInstance() {

		if (logfile == null) {
			synchronized (Logger.class) {
				if (logfile == null) {
					System.out
							.println("getInstance(): First time getInstance was invoked!");
					logfile = new Logger();
				}
			}
		}
		return logfile;

	}

	// For Observer Pattern

	@Override
	public void update(Observable o, Object arg) {
		String event = (String) arg;
		file = new File(Main.raw_text_filename);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			writer = new FileWriter(file.getAbsoluteFile(), true);
			writer.write(event + "\n");
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}

	}

}
