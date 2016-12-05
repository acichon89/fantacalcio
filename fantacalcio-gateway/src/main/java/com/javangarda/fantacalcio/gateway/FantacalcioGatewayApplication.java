package com.javangarda.fantacalcio.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class FantacalcioGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantacalcioGatewayApplication.class, args);
	}
}
