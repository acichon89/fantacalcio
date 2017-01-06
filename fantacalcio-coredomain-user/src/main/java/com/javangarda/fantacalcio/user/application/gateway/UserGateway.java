package com.javangarda.fantacalcio.user.application.gateway;

import com.javangarda.fantacalcio.user.application.data.ChangePasswordDTO;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import com.javangarda.fantacalcio.user.application.event.PasswordNotMatchException;
import com.javangarda.fantacalcio.user.application.model.User;

public interface UserGateway {

	void registerUser(RegistrationUserDTO registrationUserDTO) throws DuplicateEmailException;
	void startConfirmationEmailProcedure(String mail, String userId) throws EmailNotFoundException;
	void confirmEmail(String activationToken) throws EmailNotFoundException;

	void changePassword(ChangePasswordDTO changePasswordDTO) throws PasswordNotMatchException;
}
