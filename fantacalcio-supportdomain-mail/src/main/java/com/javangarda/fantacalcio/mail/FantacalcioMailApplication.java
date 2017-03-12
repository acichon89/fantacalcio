package com.javangarda.fantacalcio.mail;

import com.javangarda.fantacalcio.mail.infrastructure.port.adapter.messaging.Events;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Events.class)
public class FantacalcioMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantacalcioMailApplication.class, args);
	}
	
	
}
