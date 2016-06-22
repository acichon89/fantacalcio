package com.javangarda.fantacalcio.web.pages;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.javangarda.fantacalcio.web.contexts.PageObject;

@PageObject
public class Site {

	@Autowired
	private WebDriver webDriver;
	@Value("${webapp.mainurl}")
	private String url;
	@Autowired
	private HelloWorldPage helloWorldPage;
	
	public HelloWorldPage openHelloWorldPage(){
		this.webDriver.get(url+"login");
		return helloWorldPage;
	}
}
