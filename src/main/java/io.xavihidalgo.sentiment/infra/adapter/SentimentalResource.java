package io.xavihidalgo.sentiment.infra.adapter;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.xavihidalgo.sentiment.domain.Sentiment;
import io.xavihidalgo.sentiment.domain.SentimentAnalysis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/sentiment-analysis")
@Produces(MediaType.APPLICATION_JSON)
public class SentimentalResource {

	private final SentimentAnalysis analyzer;

	public SentimentalResource(SentimentAnalysis analyzer) {
		this.analyzer = analyzer;
	}

	@GET
	@Timed
	public SentimentReport saySentiment(@QueryParam("sentence") String sentence) {
		final String safeSentence = sentence;
		final Sentiment sentiment = analyzer.sentimentOf(safeSentence);
		return new SentimentReport(safeSentence, sentiment.toString());
	}
}