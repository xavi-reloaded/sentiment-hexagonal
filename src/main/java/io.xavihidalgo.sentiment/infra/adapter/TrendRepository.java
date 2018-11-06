package io.xavihidalgo.sentiment.infra.adapter;

import io.xavihidalgo.sentiment.annotation.Repository;
import io.xavihidalgo.sentiment.domain.Sentiment;
import io.xavihidalgo.sentiment.domain.Trend;
import io.xavihidalgo.sentiment.infra.AuditDAO;
import io.xavihidalgo.sentiment.infra.IdSequence;

import java.sql.Timestamp;

@Repository
public class TrendRepository implements Trend {

	private final AuditDAO auditDao;

	public TrendRepository(AuditDAO auditDao) {
		this.auditDao = auditDao;
	}

	public void record(String sentence, Sentiment sentiment) {
		final Timestamp timestamp = new Timestamp(now());
		auditDao.insert(IdSequence.INSTANCE.next(), timestamp, sentence, sentiment.name());
	}

	private long now() {
		return new java.util.Date().getTime();
	}

}
