package com.javangarda.fantacalcio.util.time;

import org.joda.time.DateTime;

public interface DateAndTimeResolver {

	String DEFAULT_TIMEZONE_NAME = "Europe/Warsaw";
	
	DateTime getCurrentDateTime();
}
