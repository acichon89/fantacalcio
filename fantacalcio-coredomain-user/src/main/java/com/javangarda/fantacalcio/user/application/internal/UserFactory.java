package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.model.User;

public interface UserFactory {

	User create(RegistrationUserDTO registrationUserDTO);
}
