package com.javangarda.fantacalcio.user.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javangarda.fantacalcio.user.domain.repository.UserRepository;
import com.javangarda.fantacalcio.user.domain.service.QueryDrivenUserDetailsService;

@Configuration
public class UserSecurityContext {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
	
	@Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new QueryDrivenUserDetailsService(userRepository);
    }
}
