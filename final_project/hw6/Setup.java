package project.hw6;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author alikozlu
 * 
 *         Class responsible for setting up the necessary connection with the
 *         Twitter Api and returning a Twitter object for us to use.
 */
public class Setup {

	private ConfigurationBuilder cb;

	public Setup(ConfigurationBuilder cb) {
		this.cb = cb;

	}

	public Twitter getTwitterObject() {

		// Receive the ConfigurationBuilder object
		cb = new ConfigurationBuilder();

		// Set the necessary authorizations. Make sure we have all the necessary
		// connections (such as being able to receive JSON Data.)
		cb.setDebugEnabled(true);

		cb.setOAuthConsumerKey("mbeUyGNMc9lfqmnihBG6DdWR0");
		cb.setOAuthConsumerSecret("qYk3XI0Bt1KfYNeCY9XlazK45kwT4s2IhLkS2Jqyl8Yu4u7ZMM");
		cb.setOAuthAccessToken("39365900-faEArVxxeXaOo8OkVsaxrqF0kzEDS8joM9vs9n9yu");
		cb.setOAuthAccessTokenSecret("VQ7zNP6UdERzmcD5qWuwuEK9phKCJBXIBqzBjf3ZASCoq");
		cb.setOAuthAccessTokenSecret(
				"VQ7zNP6UdERzmcD5qWuwuEK9phKCJBXIBqzBjf3ZASCoq")
				.setHttpConnectionTimeout(100000);

		cb.setJSONStoreEnabled(true);

		// create and return a twitter object
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		return twitter;

	}
}
