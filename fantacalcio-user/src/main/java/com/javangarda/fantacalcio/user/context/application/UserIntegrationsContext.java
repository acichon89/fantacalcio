package com.javangarda.fantacalcio.user.context.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan(value={"com.javangarda.fantacalcio.user.application.event"})
public class UserIntegrationsContext {

}
