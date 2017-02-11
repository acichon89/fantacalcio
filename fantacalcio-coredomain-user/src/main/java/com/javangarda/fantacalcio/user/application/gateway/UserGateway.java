package com.javangarda.fantacalcio.user.application.gateway;

import com.javangarda.fantacalcio.user.application.data.ChangePasswordDTO;
import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.data.ResetPasswordDTO;

import java.util.Optional;

public interface UserGateway {

	void registerUser(RegistrationUserDTO registrationUserDTO);
	void startConfirmationEmailProcedure(String mail, String userId);
	void startResetPasswordProcedure(String mail);
	Optional<FantaCalcioUser> confirmEmail(String activationToken);

	void changePassword(ChangePasswordDTO changePasswordDTO, String email);
	Optional<FantaCalcioUser> resetPassword(ResetPasswordDTO resetPasswordDTO);

	void ban(String mail);
}
