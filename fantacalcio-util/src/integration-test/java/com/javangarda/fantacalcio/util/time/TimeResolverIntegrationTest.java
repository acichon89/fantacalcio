package com.javangarda.fantacalcio.util.time;

import static org.junit.Assert.fail;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javangarda.fantacalcio.util.time.impl.TimeResolverImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class TimeResolverIntegrationTest {

	@Autowired
	private TimeResolver timeResolver;
	
	@Before
	public void init() {
		DateTimeUtils.setCurrentMillisFixed(1441215978591l);
	}
	
	@Test
	public void test() {
		DateTime currentTime = timeResolver.getCurrentDateTime();
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

@Configuration
class Config {

	@Bean
	public TimeResolver timeResolver() {
		return new TimeResolverImpl();
	}
}