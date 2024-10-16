package com.banyue.demo.common;

/**
 * jdk17中的包名发生变化
 import jakarta.validation.ConstraintValidator;
 import jakarta.validation.ConstraintValidatorContext;
 *
 *
 */


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<EnumValueValid, Enum<?>> {

    private List<Object> enumValues;

    @Override
    public void initialize(EnumValueValid constraintAnnotation) {
        enumValues = Arrays.stream(constraintAnnotation.getClass().getEnumConstants())
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext constraintValidatorContext) {
        return enumValues != null && enumValues.contains(value.toString());
    }
}
