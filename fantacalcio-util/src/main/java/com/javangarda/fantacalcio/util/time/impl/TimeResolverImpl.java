package com.javangarda.fantacalcio.util.time.impl;

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

}
