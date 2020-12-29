package com.orangetalents.orangetalentschallenge.util;

import com.orangetalents.orangetalentschallenge.endpoint.service.UserService;
import com.orangetalents.orangetalentschallenge.util.annotation.FieldUnique;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldUniqueValidator implements ConstraintValidator<FieldUnique, String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.contains("@")){
            return !userService.existsUserByEmail(value);
        }
        return !userService.existsUserByCpf(value);
    }
}
