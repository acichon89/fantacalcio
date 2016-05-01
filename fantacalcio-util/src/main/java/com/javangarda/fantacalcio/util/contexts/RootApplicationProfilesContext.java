package com.javangarda.fantacalcio.util.contexts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class RootApplicationProfilesContext {

	@Profile(AppProfile.LOCALDEV_DEPLOY)
	@Configuration
	@PropertySource("classpath:envs/localdev-deploy.properties")
	public static class LocaldevDeployProperties {}
	
	@Profile(AppProfile.LOCALDEV_TEST)
	@Configuration
	@PropertySource("classpath:envs/localdev-test.properties")
	public static class LocaldevTestProperties {}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
}
