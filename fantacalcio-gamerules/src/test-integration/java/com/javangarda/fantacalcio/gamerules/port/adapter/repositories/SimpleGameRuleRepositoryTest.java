package com.javangarda.fantacalcio.gamerules.port.adapter.repositories;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javangarda.fantacalcio.gamerules.domain.application.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={PersistenceContext.class})
public class SimpleGameRuleRepositoryTest {

	@Autowired
	private SimpleGameRuleRepository repo;
	
	@Test
	public void testSaveS() {
		Assert.assertNotNull(repo);
	}

}
