package io.xavihidalgo.sentiment.infra;

import io.xavihidalgo.sentiment.annotation.NotThereYet;

import java.util.Date;

@NotThereYet
public enum IdSequence {

	INSTANCE;

	public long next() {
		return new Date().getTime() - 1428355255699l;
	}
}
