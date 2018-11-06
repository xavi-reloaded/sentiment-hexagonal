package io.xavihidalgo.sentiment.domain;


public interface Lexicon {

	Sentiment get(String key);

}