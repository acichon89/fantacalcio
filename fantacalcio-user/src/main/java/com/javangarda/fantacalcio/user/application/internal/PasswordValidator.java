package com.javangarda.fantacalcio.user.application.internal;

import com.javangarda.fantacalcio.util.validate.DataNotValidException;

public interface PasswordValidator {

	void validate(String password) throws DataNotValidException;
}
