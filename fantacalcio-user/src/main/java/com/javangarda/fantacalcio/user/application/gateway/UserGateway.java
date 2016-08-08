package com.javangarda.fantacalcio.user.application.gateway;

import org.springframework.social.connect.Connection;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;

public interface UserGateway {

	void registerUser(RegistrationUserDto registrationUserDto) throws DuplicateEmailException;
	
	void startConfirmationEmailProcedure(String mail) throws EmailNotFoundException;
	
	void saveConnection(String email, Connection connection);
}
