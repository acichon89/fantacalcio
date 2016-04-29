package com.javangarda.fantacalcio.web.contexts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.javangarda.fantacalcio.web.pages")
public class SeleniumContext {

	@Bean
	public Scope seleniumDriverScope(){
		return new SeleniumDriverScope();
	}
	
	@Bean
	public CustomScopeConfigurer customScopeConfigurer(){
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.addScope("selenium-driver-scope", seleniumDriverScope());
		return configurer;
	}
	
	@Bean
	public BeanPostProcessor beanPostProcessor() {
		return new PageObjectPostProcessor();
	}
	
	@Bean
	public FirefoxDriver firefoxDriver(){
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return driver;
	}
	

	
}
