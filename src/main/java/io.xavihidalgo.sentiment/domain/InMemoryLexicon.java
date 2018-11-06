package io.xavihidalgo.sentiment.domain;

import io.xavihidalgo.sentiment.annotation.NotThereYet;

import java.util.Map;

@NotThereYet
public class InMemoryLexicon implements Lexicon {
	private Map<String, Sentiment> dictionary;

	public InMemoryLexicon(Map<String, Sentiment> dictionary) {
		this.dictionary = dictionary;
	}

	public Sentiment get(String key) {
		return dictionary.get(key);
	}

}