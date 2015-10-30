package project.hw6;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;

public class TweetReaderTest {

	private String JSON_filename = "";
	private String raw_text_filename = "";
	private static PrintStream writer;
	private TweetWriter tweetWriter;
	private static File file;

	private TweetReader tweetReader;

	@Before
	public void setUp() throws Exception {

		// setup up the connection
		ConfigurationBuilder cb = new ConfigurationBuilder(); // Acreditacion
		Setup setup = new Setup(cb);

		JSON_filename = "twitterjsondata.json";
		raw_text_filename = "testfile.txt";

		// get the twitter object
		Twitter twitter = setup.getTwitterObject();
		tweetWriter = new TweetWriter(JSON_filename, file, writer);

		tweetReader = new TweetReader(twitter, tweetWriter);

	}

	@Test
	public void validateWordMap() throws IOException {

		Logger filelogger = Logger.getInstance();

		tweetReader.addObserver(filelogger);

		tweetReader.readTweets();
		tweetReader.read_tweet_texts();

		ReadFile r = new ReadFile(raw_text_filename);
		r.readFile();

		HashMap<String, Integer> actualmap = tweetReader.getWordMap();
		HashMap<String, Integer> expectedmap = r.getWordMap();

		assertEquals(actualmap.get("hate"), expectedmap.get("hate"));

		assertEquals(actualmap.get("great"), expectedmap.get("great"));
	}
}
