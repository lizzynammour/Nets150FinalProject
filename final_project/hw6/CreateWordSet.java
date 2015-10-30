package project.hw6;

import java.util.Set;

public class CreateWordSet {
	private Set<String> chillWords;
	private Set<String> neuroticWords;

	public CreateWordSet(Set<String> neuroticWords, Set<String> chillWords) {

		this.chillWords = chillWords;
		this.neuroticWords = neuroticWords;

	}

	public void createSets() {
		chillWords.add("cool");
		chillWords.add("chill");
		chillWords.add("sweet");
		chillWords.add("nice");
		chillWords.add("fun");
		chillWords.add("lovely");
		chillWords.add("kind");
		chillWords.add("mellow");
		chillWords.add("woah");
		chillWords.add("ok");
		chillWords.add("okay");
		chillWords.add("awesome");
		chillWords.add("yay");
		chillWords.add("yes");
		chillWords.add("great");

		neuroticWords.add("omg");
		neuroticWords.add("wtf");
		neuroticWords.add("ugh");
		neuroticWords.add("hate");
		neuroticWords.add("wow");
		neuroticWords.add("seriously");
		neuroticWords.add("no");
		neuroticWords.add("awful");
		neuroticWords.add("terrible");
		neuroticWords.add("unbelievable");
		neuroticWords.add("worst");
		neuroticWords.add("disgusting");
		neuroticWords.add("crazy");
		
	}

	public Set<String> getNeuroticSet() {
		return neuroticWords;
	}

	public Set<String> getChillSet() {
		return chillWords;
	}
}
