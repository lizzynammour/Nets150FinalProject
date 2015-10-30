package project.hw6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

/**
 * @author alikozlu
 * 
 *         Class responsible for reading the tweets. It reads the tweets from a
 *         certain geolocation, saves it as json data + raw string format
 */
public class TweetReader extends Observable {

	private Twitter twitter;
	private TweetWriter TweetWriter;

	private Logger logfile;

	private Hashtable<Date, String> tweet_table;
	private HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

	public TweetReader(Twitter twitter, TweetWriter TweetWriter) {

		this.TweetWriter = TweetWriter;
		this.twitter = twitter;
		logfile = Logger.getInstance();
		tweet_table = new Hashtable<Date, String>();
	}

	/**
	 * The method responsible for reading the tweets and storing them into JSON
	 * formatted file + text file consisting texts of tweets
	 * 
	 * @throws IOException
	 */

	public void readTweets() throws IOException {

		// give the input
		double lat = +47.60890;
		double lon = -122.33700;

		// determine the radius
		double res = 5;

		// mile or kilometer
		String resUnit = "mi";

		// determine the number of wanted tweets
		int wantedTweets = 2000;
		int remainingTweets = wantedTweets;

		Query query = new Query().geoCode(new GeoLocation(lat, lon), res,
				resUnit);
		List<Status> tweetss = new ArrayList<Status>();

		try {

			while (remainingTweets > 0) {

				remainingTweets = wantedTweets - tweetss.size();

				if (remainingTweets > 100) {

					query.count(100);
				} else {

					query.count(remainingTweets);

				}

				QueryResult result = twitter.search(query);

				// tweetss includes 100 tweets per read
				tweetss.addAll(result.getTweets());

				for (Status s : tweetss) {

					// hashtable is used so that we don't read the same tweet
					// multiple times. Key for hashtable is unique tweet ID.
					// Ofcourse re-tweets are read multiple times since they are
					// from different users

					if (!tweet_table.contains(s.getId())) {

						// put it to table
						String text = s.getText();
						tweet_table.put(s.getCreatedAt(), text);

						// get json version
						String rawJSON = TwitterObjectFactory.getRawJSON(s);

						if (rawJSON != null) {

							// write it to json file
							TweetWriter.storeJSON(rawJSON);
						}
					}
				}

				// repeadetely read tweets
				Status s = tweetss.get(tweetss.size() - 1);
				long firstQueryID = s.getId();
				query.setMaxId(firstQueryID);
				remainingTweets = wantedTweets - tweetss.size();

			}

		} catch (TwitterException te) {
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
	}

	/**
	 * Adds the texts of the tweets to a text file.
	 * 
	 */

	public HashMap<String, Integer> getWordMap() {
		for (Entry<Date, String> e : tweet_table.entrySet()) {
			String text = e.getValue();

			String[] words = text.split(" ");
			for (int i = 0; i < words.length; i++) {

				if (!wordMap.containsKey(words[i].toLowerCase())) {
					wordMap.put(words[i].toLowerCase(), 1);
				} else {
					int value = wordMap.get(words[i].toLowerCase());
					wordMap.put(words[i].toLowerCase(), value + 1);
				}
			}
		}
		return wordMap;
	}

	public void read_tweet_texts() {

		for (Entry<Date, String> e : tweet_table.entrySet()) {

			setChanged();
			notifyObservers(e.getValue());

		}
	}

}
