package io.xavihidalgo.sentiment.infra.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class SentimentReportTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		final SentimentReport person = new SentimentReport("Hello Kitten", "HAPPY");
		assertThat(MAPPER.writeValueAsString(person)).isEqualTo(fixture("fixtures/sentiment-report.json"));
	}
}
