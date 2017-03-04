package com.javangarda.fantacalcio.user;

import com.javangarda.fantacalcio.commons.authentication.CurrentUserResolver;
import com.javangarda.fantacalcio.commons.authentication.SecurityContextCurrentUserResolver;
import com.javangarda.fantacalcio.commons.validation.PasswordCheckValidator;
import com.javangarda.fantacalcio.commons.validation.RepositoryFieldExistsValidator;
import com.javangarda.fantacalcio.commons.validation.RepositoryFieldUniqueValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
public class FantacalcioUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantacalcioUserApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public CurrentUserResolver currentUserResolver() {
		return new SecurityContextCurrentUserResolver();
	}

	@Primary
	@Bean
	public RepositoryFieldUniqueValidator repositoryFieldUniqueValidator(JdbcTemplate jdbcTemplate){
		return new RepositoryFieldUniqueValidator(jdbcTemplate);
	}

	@Primary
	@Bean
	public RepositoryFieldExistsValidator repositoryFieldExistsValidator(JdbcTemplate jdbcTemplate) {
		return new RepositoryFieldExistsValidator(jdbcTemplate);
	}

	@Primary
	@Bean
	public PasswordCheckValidator passwordCheckValidator(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, CurrentUserResolver currentUserResolver) {
		return new PasswordCheckValidator(jdbcTemplate, passwordEncoder, currentUserResolver);
	}
}
