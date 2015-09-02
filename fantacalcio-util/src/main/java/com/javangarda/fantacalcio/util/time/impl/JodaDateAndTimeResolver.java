package com.javangarda.fantacalcio.util.time.impl;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.util.time.DateAndTimeResolver;

@Component
public class JodaDateAndTimeResolver implements DateAndTimeResolver {

	@Override
	public DateTime getCurrentDateTime() {
		return DateTime.now(DateTimeZone.forID(DEFAULT_TIMEZONE_NAME));
	}

}
