package com.javangarda.fantacalcio.user.application.internal.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javangarda.fantacalcio.user.application.data.RegistrationUserDto;
import com.javangarda.fantacalcio.user.application.internal.PasswordValidator;
import com.javangarda.fantacalcio.util.messages.Message;
import com.javangarda.fantacalcio.util.messages.Severity;
import com.javangarda.fantacalcio.util.messages.ValidationMessages;
import com.javangarda.fantacalcio.util.validate.AbstractValidator;
import com.javangarda.fantacalcio.util.validate.DataNotValidException;

@Component
public class RegistrationUserDTOValidator implements AbstractValidator<RegistrationUserDto>{

	@Autowired
	private PasswordValidator passwordValidator;
	
	@Override
	public void validate(RegistrationUserDto form) throws DataNotValidException {
		ValidationMessages validationMessages = ValidationMessages.createEmpty();
		try {
			passwordValidator.validate(form.getPassword());
		} catch (DataNotValidException e) {
			validationMessages.merge(e.getValidationMessages());
		}
		if(!form.getPassword().equals(form.getConfirmedPassword())){
			validationMessages.add("password", Message.of(Severity.ERROR, "validator.register.passwordsNotEquals"));
		}
		if(StringUtils.isNotEmpty(form.getEmail())){
			validationMessages.add("email", Message.of(Severity.ERROR, "validator.register.emailempty"));
		}
		if(!EmailValidator.getInstance().isValid(form.getEmail())){
			validationMessages.add("email", Message.of(Severity.ERROR, "validator.register.emailempty", form.getEmail()));
		}
		if(!validationMessages.isEmpty()){
			throw new DataNotValidException(validationMessages);
		}
	}

}
