package io.xavihidalgo.sentiment.infra.adapter;

import io.xavihidalgo.sentiment.domain.Sentiment;
import io.xavihidalgo.sentiment.domain.SentimentAnalysis;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class SentimentalResourceTest {

	private static final SentimentAnalysis analyzer = mock(SentimentAnalysis.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new SentimentalResource(analyzer)).build();

	private final SentimentReport report = new SentimentReport("Kitten", "HAPPY");

	@Before
	public void setup() {
		when(analyzer.sentimentOf(eq("Kitten"))).thenReturn(Sentiment.HAPPY);
	}

	@Test
	public void testSentimentAnalysis() {
		assertThat(resources.client().target("/sentiment-analysis?sentence=Kitten").request().get(SentimentReport.class)).isEqualTo(report);
		verify(analyzer).sentimentOf("Kitten");
	}
}
