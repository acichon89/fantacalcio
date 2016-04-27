package com.javangarda.fantacalcio.football.domain.factories;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.javangarda.fantacalcio.football.domain.model.Club;


public class ClubFactoryTest {

	@Test
	public void createTest() {
		ClubFactory clubFactory = new ClubFactory();
		Club dummyClub = clubFactory.create();
		Assert.assertNotNull(dummyClub);
		Assert.assertTrue(StringUtils.isNotBlank(dummyClub.getId()));
	}

}
