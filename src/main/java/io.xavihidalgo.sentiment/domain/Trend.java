package io.xavihidalgo.sentiment.domain;


public interface Trend {

	void record(String sentence, Sentiment sentiment);
}
