package com.javangarda.fantacalcio.util.contexts;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={RootApplicationProfilesContext.class, RootPersistenceContext.class})
public class RootContext {

}
