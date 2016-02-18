package com.javangarda.fantacalcio.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HelloWorldPage {

	@FindBy(id="test")
	private WebElement helloSpan;
	
	public HelloWorldPage(WebDriver webDriver){
		PageFactory.initElements(webDriver, this);
	}
	
	public boolean helloSpanContains(String text){
		return text.equals(helloSpan.getText());
	}
}
