package com.javangarda.fantacalcio.user.application.internal;

import java.util.Optional;

import com.javangarda.fantacalcio.user.application.data.FantaCalcioUser;

public interface CurrentUserProvider {

	Optional<FantaCalcioUser> getCurrentLoggedInUser(); 
}
