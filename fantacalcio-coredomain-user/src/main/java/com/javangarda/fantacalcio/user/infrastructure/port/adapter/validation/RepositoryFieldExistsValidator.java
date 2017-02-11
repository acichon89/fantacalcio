package com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class RepositoryFieldExistsValidator implements ConstraintValidator<RepositoryFieldUnique, String> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String query;

    @Override
    public void initialize(RepositoryFieldUnique constraintAnnotation) {
        this.query=constraintAnnotation.query();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        int count = jdbcTemplate.queryForObject(query, new Object[] {value}, Integer.class);
        return count == 1;
    }
}
