package com.javangarda.fantacalcio.util.time.impl;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JodaDateAndTimeResolverTest {

	private TimeResolverImpl resolver = new TimeResolverImpl();
	
	@Before
	public void init() {
		DateTimeUtils.setCurrentMillisFixed(1441215978591l);
	}
	
	@Test
	public void test() {
		resolver = new TimeResolverImpl();
		DateTime currentTime = resolver.getCurrentDateTime();
		Assert.assertEquals(2015, currentTime.year().get());
		Assert.assertEquals(9, currentTime.monthOfYear().get());
		Assert.assertEquals(2, currentTime.dayOfMonth().get());
		Assert.assertEquals(19, currentTime.hourOfDay().get());
		Assert.assertEquals(46, currentTime.minuteOfHour().get());
	}
	
	@After
	public void after() {
		DateTimeUtils.setCurrentMillisSystem();
	}

}
