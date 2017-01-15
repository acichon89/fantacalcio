package com.javangarda.fantacalcio.user.infrastructure.port.adapter.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(value = {ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmedValidator.class)
@Documented
public @interface PasswordConfirmed {
    String message();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String userEmailFieldName();
}
