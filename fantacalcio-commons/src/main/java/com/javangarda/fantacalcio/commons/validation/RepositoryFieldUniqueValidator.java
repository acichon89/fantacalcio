package com.javangarda.fantacalcio.commons.validation;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RepositoryFieldUniqueValidator implements ConstraintValidator<RepositoryFieldUnique, String> {

    private JdbcTemplate jdbcTemplate;
    private String query;

    public RepositoryFieldUniqueValidator(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void initialize(RepositoryFieldUnique constraintAnnotation) {
        this.query=constraintAnnotation.query();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        int count = jdbcTemplate.queryForObject(query, new Object[] {value}, Integer.class);
        return count == 0;
    }
}
