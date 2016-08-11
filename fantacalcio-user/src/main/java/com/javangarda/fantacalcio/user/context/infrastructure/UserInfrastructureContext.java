package com.javangarda.fantacalcio.user.context.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value= {UserPersistenceContext.class, UserSocialContext.class})
public class UserInfrastructureContext {

}
