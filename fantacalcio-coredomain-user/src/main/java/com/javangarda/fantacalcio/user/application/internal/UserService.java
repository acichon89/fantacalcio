package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;

import java.util.Optional;

public interface UserService {

	String registerUser(RegistrationUserDTO registrationUserDto);
	Optional<String> assignActivationToken(String email, String userId);
	Optional<String> assignResetPasswordToken(String email);
	Optional<FantaCalcioUser> confirmEmail(String activationToken);
	void changePassword(String newPassword, String userEmail);
	Optional<FantaCalcioUser> resetPassword(String newPassword, String token);
	void banUser(String email);
}
