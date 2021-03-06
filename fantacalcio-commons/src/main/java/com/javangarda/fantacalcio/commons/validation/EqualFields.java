package com.javangarda.fantacalcio.commons.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualFieldsValidator.class)
@Documented
public @interface EqualFields {
    String message();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String baseField();
    String matchField();
}