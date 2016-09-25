package com.javangarda.fantacalcio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FantacalcioConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantacalcioConfigServerApplication.class, args);
	}
}
