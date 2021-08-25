package com.api.bookstore.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidation implements ConstraintValidator<Number,Object> {

    private Number number;

    @Override
    public void initialize(Number constraintAnnotation) {
        this.number = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }

        String regex = number.decimal() ? "-?[0-9][0-9\\.\\,]*" : "-?[0-9]+";
        String data = String.valueOf(value);
        return data.matches(regex);
    }
}
