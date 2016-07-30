package com.javangarda.fantacalcio.util.time.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.util.time.TimeResolver;

@Component
public class TimeResolverImpl implements TimeResolver {

	@Override
	public DateTime getCurrentDateTime() {
		return DateTime.now(DateTimeZone.forID(DEFAULT_TIMEZONE_NAME));
	}

	@Override
	public ZonedDateTime getCurrentZonedDateTime() {
		ZoneId zoneId = ZoneId.of(DEFAULT_TIMEZONE_NAME);
		return ZonedDateTime.now(zoneId);
	}

}
