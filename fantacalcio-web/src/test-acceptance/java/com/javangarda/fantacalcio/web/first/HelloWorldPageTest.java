package com.javangarda.fantacalcio.web.first;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.javangarda.fantacalcio.web.pages.HelloWorldPage;

public class HelloWorldPageTest {

	private WebDriver webDriver;
	
	@Before
	public void setUp(){
		this.webDriver = new FirefoxDriver();
		this.webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	@After
	public void finish() {
		this.webDriver.close();
	}
	
	@Test
	public void shouldShowSomethingTest(){
		this.webDriver.get("http://localhost:8080/fantacalcio/");
		HelloWorldPage page = new HelloWorldPage(webDriver);
		Assert.assertTrue(page.helloSpanContains("Hello, Gradle !"));
	}
}
