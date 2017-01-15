package com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation;

import com.javangarda.fantacalcio.user.application.internal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, String> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String userEmailFieldName;


    @Override
    public void initialize(PasswordConfirmed constraintAnnotation) {
        this.userEmailFieldName=constraintAnnotation.userEmailFieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String encodedPassword = passwordEncoder.encode(value);
        int count = userRepository.countUserWithPasswordAndEmail(encodedPassword, userEmailFieldName);
        return count == 0;
    }
}
