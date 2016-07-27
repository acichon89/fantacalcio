package com.javangarda.fantacalcio.util.contexts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class RootI18nContext {

	@Bean
	public ResourceBundleMessageSource messageSource() {  
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();  
        source.setBasename("i18n/messages");  
        source.setUseCodeAsDefaultMessage(true);  
        return source;  
    }   
}
