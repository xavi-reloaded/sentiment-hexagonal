package io.xavihidalgo.sentiment.infra;

import io.dropwizard.configuration.ConfigurationSourceProvider;
import io.xavihidalgo.sentiment.domain.Lexicon;
import io.xavihidalgo.sentiment.domain.SentimentAnalysis;
import io.xavihidalgo.sentiment.domain.Trend;
import io.xavihidalgo.sentiment.infra.adapter.FileBasedLexicon;
import io.xavihidalgo.sentiment.infra.adapter.SentimentalResource;
import io.xavihidalgo.sentiment.infra.adapter.TrendRepository;
import io.xavihidalgo.sentiment.infra.adapter.TwitterAdapter;
import io.xavihidalgo.sentiment.infra.audio.SamplePlayer;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

import java.io.IOException;
import java.io.InputStream;

public class SentimentalApplication extends Application<SentimentalConfiguration> {

	public static void main(String[] args) throws Exception {
		new SentimentalApplication().run(args);
	}

	@Override
	public String getName() {
		return "sentimental";
	}

	@Override
	public void initialize(Bootstrap<SentimentalConfiguration> bootstrap) {

	}

	@Override
	public void run(SentimentalConfiguration configuration, Environment environment) {
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);

		// SPI Lexicon
		final Lexicon lexicon = new FileBasedLexicon(configuration.getLexiconFileName());

		// SPI Trend
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
		final AuditDAO auditDao = jdbi.onDemand(AuditDAO.class);
		final Trend audit = new TrendRepository(auditDao);

		// Domain Model
		final SentimentAnalysis service = new SentimentAnalysis(lexicon, audit);

		// API RESTful
		final SentimentalResource resource = new SentimentalResource(service);
		environment.jersey().register(resource);

		// API Twitter
		final TwitterAdapter twitterAdapter = new TwitterAdapter(service, new SamplePlayer());
		twitterAdapter.subscribe("devoxx, #memepasmal, @cyriux, @tpierrain, arolla");
		// start
	}

}