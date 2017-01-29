package com.javangarda.fantacalcio.user;

import com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation.RepositoryFieldUniqueValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
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

	@Primary
	@Bean
	public RepositoryFieldUniqueValidator repositoryFieldUniqueValidator(){
		return new RepositoryFieldUniqueValidator();
	}

}
