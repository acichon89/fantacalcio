package com.javangarda.fantacalcio.user.application.gateway;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;

public interface UserGateway {

	void registerUser(RegistrationUserDto registrationUserDto) throws DuplicateEmailException;
	
	void confirmEmail(String mail) throws EmailNotFoundException;
}
