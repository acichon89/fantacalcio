package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;

public interface UserService {

	void registerUser(RegistrationUserDTO registrationUserDto) throws DuplicateEmailException;
	String assignActivationToken(String email) throws EmailNotFoundException;
}
