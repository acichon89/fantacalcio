package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.user.application.data.EstablishedUserSocialConnection;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.util.factories.EntityFactory;

public interface UserFactory extends EntityFactory<User>{

	User create(EstablishedUserSocialConnection connection);
}
