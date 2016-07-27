package com.javangarda.fantacalcio.user.application.internal.impl;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.util.validate.AbstractValidator;
import com.javangarda.fantacalcio.util.validate.DataNotValidException;

@Component
public class RegistrationUserDTOValidator implements AbstractValidator<RegistrationUserDto>{

	@Override
	public void validate(RegistrationUserDto form) throws DataNotValidException {
		String email = form.getEmail();
	}

}
