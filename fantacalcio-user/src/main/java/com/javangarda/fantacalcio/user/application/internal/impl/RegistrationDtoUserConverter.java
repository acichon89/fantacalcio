package com.javangarda.fantacalcio.user.application.internal.impl;

import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.util.convert.Converter;

@Component
public class RegistrationDtoUserConverter implements Converter<RegistrationUserDto, User>{

	@Override
	public User convertTo(RegistrationUserDto s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegistrationUserDto convertFrom(User d) {
		// TODO Auto-generated method stub
		return null;
	}

}
