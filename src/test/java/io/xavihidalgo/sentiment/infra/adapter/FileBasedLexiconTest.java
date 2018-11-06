package io.xavihidalgo.sentiment.infra.adapter;

import io.xavihidalgo.sentiment.domain.Sentiment;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileBasedLexiconTest {

	@Test
	public void testLexicon() throws Exception {
		final FileBasedLexicon lexicon = new FileBasedLexicon("lexicon.properties");
		assertEquals(Sentiment.HAPPY, lexicon.get("devoxx"));
		assertEquals(Sentiment.SAD, lexicon.get("framework"));
	}
}
