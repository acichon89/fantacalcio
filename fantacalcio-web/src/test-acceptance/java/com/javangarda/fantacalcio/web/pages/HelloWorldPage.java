package com.javangarda.fantacalcio.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.javangarda.fantacalcio.web.contexts.PageObject;

@PageObject
public class HelloWorldPage {

	@FindBy(id="test")
	private WebElement helloSpan;
	
	public boolean helloSpanContains(String text){
		return text.equals(helloSpan.getText());
	}
}
