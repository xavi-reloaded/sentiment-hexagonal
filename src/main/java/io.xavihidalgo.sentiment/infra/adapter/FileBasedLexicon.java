package io.xavihidalgo.sentiment.infra.adapter;

import io.xavihidalgo.sentiment.domain.Lexicon;
import io.xavihidalgo.sentiment.domain.Sentiment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileBasedLexicon implements Lexicon {

	private final Properties properties = new Properties();


	public FileBasedLexicon(String fileName) {
		final InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
		if (is != null) {
			try {
				properties.load(is);
			} catch (IOException e) {
				throw new RuntimeException("Could not load property file '" + fileName);
			}
		} else {
			throw new RuntimeException("property file '" + fileName + "' not found in the classpath");
		}
	}

	public Sentiment get(String key) {
		final String value = properties.getProperty(key);
		return value == null ? Sentiment.NEUTRAL : Sentiment.valueOf(value);
	}

	@Override
	public String toString() {
		return "File-Based Lexicon: " + properties.size() + " entries";
	}
}
