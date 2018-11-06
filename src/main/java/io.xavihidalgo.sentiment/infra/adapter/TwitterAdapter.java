package io.xavihidalgo.sentiment.infra.adapter;

import com.twitter.hbc.twitter4j.handler.StatusStreamHandler;
import io.xavihidalgo.sentiment.domain.Sentiment;
import io.xavihidalgo.sentiment.domain.SentimentAnalysis;
import io.xavihidalgo.sentiment.infra.TwitterStream;
import io.xavihidalgo.sentiment.infra.TwitterStream.DefaultStatusStreamHandler;
import io.xavihidalgo.sentiment.infra.audio.SamplePlayer;
import twitter4j.Status;

import java.io.File;

public class TwitterAdapter {

	private final SamplePlayer samplePlayer;
	private final SentimentAnalysis service;
	private final TwitterStream twitterStream = new TwitterStream();

	public TwitterAdapter(final SentimentAnalysis service, SamplePlayer samplePlayer) {
		this.service = service;
		this.samplePlayer = samplePlayer;
	}

	public void subscribe(final String... terms) {
		final StatusStreamHandler listener = new DefaultStatusStreamHandler() {

			public void onStatus(Status status) {
				final Sentiment sentiment = service.sentimentOf(status.getText());
				System.out.println("*********************");
				System.out.println(status.getText() + " => " + sentiment);

				final String sampleName = sentiment.toString().toLowerCase();
				final File file = new File(sampleName + ".wav");
				samplePlayer.play(file);
			}
		};

		twitterStream.subscribe(listener, terms);
	}

}
