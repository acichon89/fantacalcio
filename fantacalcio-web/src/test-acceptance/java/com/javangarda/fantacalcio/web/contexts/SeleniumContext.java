package com.javangarda.fantacalcio.web.contexts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
	@Profile("selenium-driver-firefox")
	public FirefoxDriver firefoxDriver(){
		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return driver;
	}
	
	@Bean
	@Profile("selenium-driver-firefox-remote")
	public FirefoxDriver firefoxDriverRemote(){
		FirefoxBinary binary = new FirefoxBinary(new File("/usr/local/bin/firefox"));
	    binary.setEnvironmentProperty("DISPLAY",System.getProperty("lmportal.xvfb.id",":99"));
	    FirefoxDriver driver = new FirefoxDriver(binary,null);
	    return driver;
	}
	
	@Bean
	@Profile("selenium-chrome-driver")
	public ChromeDriver chromeDriver(){
		return new ChromeDriver();
	}
	
	@Bean
	@Profile("selenium-ie-driver")
	public InternetExplorerDriver ieDriver(){
		return new InternetExplorerDriver();
	}
	
}
