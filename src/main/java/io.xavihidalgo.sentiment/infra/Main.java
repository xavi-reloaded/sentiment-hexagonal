package io.xavihidalgo.sentiment.infra;

import io.xavihidalgo.sentiment.domain.Sentiment;
import io.xavihidalgo.sentiment.domain.SentimentAnalysis;
import io.xavihidalgo.sentiment.infra.adapter.CommandLineAdapter;

import java.util.Collections;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// Lexicon Adapter
		Map<String, Sentiment> dictionary = Collections.singletonMap("Kamoulox", Sentiment.HAPPY);
		
		// Domain Model
		final SentimentAnalysis service = new SentimentAnalysis(dictionary);
		
		// API Adapters
		final CommandLineAdapter adapter = new CommandLineAdapter(service, System.out);
		
		// start
		adapter.adapt(args);
	}

}
