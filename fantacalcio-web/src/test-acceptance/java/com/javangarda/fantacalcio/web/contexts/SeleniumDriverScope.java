package com.javangarda.fantacalcio.web.contexts;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class SeleniumDriverScope implements Scope {

	private Map<String, WebDriver> cache;
	
	public SeleniumDriverScope() {
		this.cache = new HashMap<String, WebDriver>();
	}
	
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (!cache.containsKey(name)) {
			WebDriver driver = (WebDriver) objectFactory.getObject();
			cache.put(name, driver);
	  }
	  return cache.get(name);
	}

	@Override
	public Object remove(String name) {
		return cache.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// empty
	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		return null;
	}
	
	public void resetDriversInCache(){
		this.cache.clear();
	}

}
