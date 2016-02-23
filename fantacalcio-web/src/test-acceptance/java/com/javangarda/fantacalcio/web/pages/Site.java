package com.javangarda.fantacalcio.web.pages;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.javangarda.fantacalcio.web.contexts.PageObject;

@PageObject
public class Site {

	@Autowired
	private WebDriver webDriver;
	
	@Autowired
	private HelloWorldPage helloWorldPage;
	
	public HelloWorldPage openHelloWorldPage(){
		this.webDriver.get("http://localhost:8080/fantacalcio/");
		return helloWorldPage;
	}
}
