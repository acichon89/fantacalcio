package com.javangarda.fantacalcio.football.domain.factories;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.javangarda.fantacalcio.football.domain.model.Player;


public class PlayerFactoryTest {

	@Test
	public void createTest() {
		PlayerFactory playerFactory = new PlayerFactory();
		Player dummyPlayer = playerFactory.create();
		Assert.assertNotNull(dummyPlayer);
		Assert.assertTrue(StringUtils.isNotBlank(dummyPlayer.getId()));
	}

}
