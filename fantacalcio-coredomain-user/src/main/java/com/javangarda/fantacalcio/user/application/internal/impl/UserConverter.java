package com.javangarda.fantacalcio.user.application.internal.impl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.extras.Converter;
import com.javangarda.fantacalcio.user.application.data.RegistrationUserDTO;
import com.javangarda.fantacalcio.user.application.model.User;

@Component
public class UserConverter implements Converter<RegistrationUserDTO, User>{

	private Mapper mapper = new DozerBeanMapper();
	
	@Override
	public User convertTo(RegistrationUserDTO s) {
		return mapper.map(s, User.class);
	}

	@Override
	public RegistrationUserDTO convertFrom(User d) {
		return mapper.map(d, RegistrationUserDTO.class);
	}

}
