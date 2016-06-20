package com.javangarda.fantacalcio.user.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages="com.javangarda.fantacalcio.user.domain", excludeFilters = @ComponentScan.Filter(value = Configuration.class, type=FilterType.ANNOTATION))
public class UserDomainContext {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
