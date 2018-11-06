package io.xavihidalgo.sentiment.infra.adapter;

import io.xavihidalgo.sentiment.domain.Sentiment;
import io.xavihidalgo.sentiment.domain.SentimentAnalysis;

import java.io.PrintStream;

/**
 * Adapter from args to 
 */
public class CommandLineAdapter {

	private final SentimentAnalysis analysis;
	private final PrintStream sentimentPublisher;

	public CommandLineAdapter(SentimentAnalysis analysis, PrintStream sentimentPublisher) {
		this.analysis = analysis;
		this.sentimentPublisher = sentimentPublisher;
	}

	public void adapt(String[] args) {
		for (String sentence : args) {
			final Sentiment sentiment = analysis.sentimentOf(sentence);
			sentimentPublisher.println(sentiment.toString());
		}
	}

	
}
