package com.javangarda.fantacalcio.user.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={
		UserPersistenceContext.class, 
		UserSocialContext.class,
		UserDomainContext.class
		})
public class UserContext {

}
