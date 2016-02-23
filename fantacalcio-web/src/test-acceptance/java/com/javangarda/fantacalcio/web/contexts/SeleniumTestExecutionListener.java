package com.javangarda.fantacalcio.web.contexts;

import org.openqa.selenium.WebDriver;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class SeleniumTestExecutionListener extends AbstractTestExecutionListener {

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		System.out.println("RESET !!!");
		testContext.getApplicationContext().getBean(SeleniumDriverScope.class).resetDriversInCache();
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		System.out.println("CLOSSSE !!");
		testContext.getApplicationContext().getBean(WebDriver.class).close();
	}
	
}
