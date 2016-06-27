package com.javangarda.fantacalcio.user.domain.factories;

import com.javangarda.fantacalcio.user.domain.model.User;
import com.javangarda.fantacalcio.user.domain.value.EstablishedUserSocialConnection;
import com.javangarda.fantacalcio.util.factories.EntityFactory;

public interface UserFactory extends EntityFactory<User>{

	User create(EstablishedUserSocialConnection connection);
}
