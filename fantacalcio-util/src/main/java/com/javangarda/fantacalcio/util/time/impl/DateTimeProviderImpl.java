package com.javangarda.fantacalcio.util.time.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.util.time.TimeResolver;

@Component
public class DateTimeProviderImpl implements DateTimeProvider {

	@Autowired
	private TimeResolver timeResolver;
	
	@Override
	public Calendar getNow() {
		return GregorianCalendar.from(timeResolver.getCurrentZonedDateTime());
	}

}
