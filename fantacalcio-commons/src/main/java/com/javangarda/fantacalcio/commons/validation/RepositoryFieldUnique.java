package com.javangarda.fantacalcio.commons.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(value = {ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RepositoryFieldUniqueValidator.class)
@Documented
public @interface RepositoryFieldUnique {
    String message();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String query();
}
