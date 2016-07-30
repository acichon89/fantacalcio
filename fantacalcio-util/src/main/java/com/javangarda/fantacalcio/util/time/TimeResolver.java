package com.javangarda.fantacalcio.util.time;

import java.time.ZonedDateTime;

import org.joda.time.DateTime;

public interface TimeResolver {

	String DEFAULT_TIMEZONE_NAME = "Europe/Warsaw";
	
	DateTime getCurrentDateTime();
	
	ZonedDateTime getCurrentZonedDateTime();
}
