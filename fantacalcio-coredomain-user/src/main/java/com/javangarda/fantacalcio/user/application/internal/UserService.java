package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;

import java.util.Optional;

public interface UserService {

	String registerUser(RegistrationUserDTO registrationUserDto);
	String assignActivationToken(String email, String userId);
	Optional<FantaCalcioUser> confirmEmail(String activationToken);
	void changePassword(String newPassword, String userEmail);
	void resetPassword(String newPassword, String userEmail);
	void banUser(String email);

	Optional<FantaCalcioUser> getById(String id);
}
