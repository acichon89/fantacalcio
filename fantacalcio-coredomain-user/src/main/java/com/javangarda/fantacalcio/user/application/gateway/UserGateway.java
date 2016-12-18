package com.javangarda.fantacalcio.user.application.gateway;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.model.User;

public interface UserGateway {

	void registerUser(RegistrationUserDTO registrationUserDTO) throws DuplicateEmailException;
	void startConfirmationEmailProcedure(String mail) throws EmailNotFoundException;
}
