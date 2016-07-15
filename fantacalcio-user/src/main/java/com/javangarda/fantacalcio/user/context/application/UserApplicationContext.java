package com.javangarda.fantacalcio.user.context.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={UserComponentsContext.class, UserIntegrationsContext.class})
public class UserApplicationContext {

}
