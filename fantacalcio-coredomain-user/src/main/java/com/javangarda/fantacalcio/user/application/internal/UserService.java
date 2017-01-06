package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.event.DuplicateEmailException;
import com.javangarda.fantacalcio.user.application.event.EmailNotFoundException;
import org.hibernate.validator.constraints.Email;

import java.util.Optional;

public interface UserService {

	String registerUser(RegistrationUserDTO registrationUserDto) throws DuplicateEmailException;
	String assignActivationToken(String email, String userId);
	String confirmEmail(String activationToken) throws EmailNotFoundException;
	void changePassword(String newPassword, String userId);

	Optional<FantaCalcioUser> getById(String id);
}
