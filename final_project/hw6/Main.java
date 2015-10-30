package project.hw6;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

	private static File file;
	private static PrintStream writer;
	private static String JSON_filename = "twitterjsondata.json";
	public static String raw_text_filename = "testfile.txt";
	private static Set<String> chillWords;
	private static Set<String> neuroticWords;

	public static void main(String[] args) throws IOException {

		// get the logger
		Logger filelogger = Logger.getInstance();

		// setup up the connection
		ConfigurationBuilder cb = new ConfigurationBuilder(); // Acreditacion
		Setup setup = new Setup(cb);

		// get the twitter object
		Twitter twitter = setup.getTwitterObject();

		// set up readers & writers
		TweetWriter tweetWriter = new TweetWriter(JSON_filename, file, writer);
		TweetReader tweetReader = new TweetReader(twitter, tweetWriter);

		// read the tweets
		tweetReader.readTweets();
		HashMap<String, Integer> wordMap = tweetReader.getWordMap();

		chillWords = new HashSet<String>();
		neuroticWords = new HashSet<String>();

		// Create the Required Sets
		CreateWordSet createWords = new CreateWordSet(neuroticWords, chillWords);
		createWords.createSets();

		int chillTotal = 0;

		for (String s : chillWords) {
			if (wordMap.containsKey(s)) {
				System.out.println(s + ": " + wordMap.get(s));
				chillTotal += wordMap.get(s);
			}
		}
		int neuroticTotal = 0;
		for (String s : neuroticWords) {
			if (wordMap.containsKey(s)) {
				System.out.println(s + ": " + wordMap.get(s));
				neuroticTotal += wordMap.get(s);
			}
		}
		System.out.println("CHILL TOTAL: " + chillTotal);
		System.out.println("NEUROTIC TOTAL: " + neuroticTotal);
		System.out.println("CHILL/NEUROTIC RATIO: " + (double) chillTotal
				/ neuroticTotal);

		// close writer
		writer = tweetWriter.getWriter();

		writer.close();
		tweetReader.read_tweet_texts();

	}
}