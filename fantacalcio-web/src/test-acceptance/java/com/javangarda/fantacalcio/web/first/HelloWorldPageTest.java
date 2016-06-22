package com.javangarda.fantacalcio.web.first;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.javangarda.fantacalcio.web.contexts.SeleniumContext;
import com.javangarda.fantacalcio.web.contexts.SeleniumTestExecutionListener;
import com.javangarda.fantacalcio.web.pages.HelloWorldPage;
import com.javangarda.fantacalcio.web.pages.Site;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SeleniumContext.class})
@TestExecutionListeners({SeleniumTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
public class HelloWorldPageTest {

	@Autowired
	private Site site;
	
	@Test
	public void shouldShowSomethingTest(){
		HelloWorldPage page = site.openHelloWorldPage();
		Assert.assertTrue(page.helloSpanContains("Hello"));
	}
}
