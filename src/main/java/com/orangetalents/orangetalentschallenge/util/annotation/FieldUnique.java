package com.orangetalents.orangetalentschallenge.util.annotation;

import com.orangetalents.orangetalentschallenge.util.FieldUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {FieldUniqueValidator.class})
public @interface FieldUnique {
    String message() default "FieldUnique.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
