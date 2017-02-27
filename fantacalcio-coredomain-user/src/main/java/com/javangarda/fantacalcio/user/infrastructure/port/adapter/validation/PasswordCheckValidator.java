package com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PasswordCheckValidator implements ConstraintValidator<PasswordCheck, String> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Optional<String> userEmail;
    private final static String query = "SELECT password FROM users WHERE email= ?";

    @Override
    public void initialize(PasswordCheck constraintAnnotation) {
        this.userEmail = getCurrentUserUsername();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(userEmail.isPresent()){
            String currentUserEmail = userEmail.get();
            String encodedPassword = jdbcTemplate.queryForObject(query, new Object[] {currentUserEmail}, String.class);
            return passwordEncoder.matches(value, encodedPassword);
        } else {
            return false;
        }
    }

    protected Optional<String> getCurrentUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of(authentication.getName());
        } else {
            return Optional.empty();
        }
    }
}
