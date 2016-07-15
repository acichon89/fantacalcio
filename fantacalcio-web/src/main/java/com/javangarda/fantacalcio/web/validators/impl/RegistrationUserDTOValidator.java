package com.javangarda.fantacalcio.web.validators.impl;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.web.validators.GuiDtoNotValidException;
import com.javangarda.fantacalcio.web.validators.GuiDtoValidator;

@Component
public class RegistrationUserDTOValidator implements GuiDtoValidator<RegistrationUserDto>{

	@Override
	public void validate(RegistrationUserDto form) throws GuiDtoNotValidException {
		String email = form.getEmail();
	}

}
