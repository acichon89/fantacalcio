package com.javangarda.fantacalcio.user.application.internal.impl;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.util.convert.AbstractDozerConverter;

@Component
public class RegistrationDtoUserConverter extends AbstractDozerConverter<RegistrationUserDto, User>{

	public RegistrationDtoUserConverter() {
		super(RegistrationUserDto.class, User.class);
	}
}
