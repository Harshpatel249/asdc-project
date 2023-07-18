package com.group6.commune.Validators;

import com.group6.commune.Model.Event;
import com.group6.commune.Model.Interest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class Interestvalidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interestId", "field.required","Event Name should not be empty or null.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interestName", "field.required","Event description Name should not be empty or null.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interestCategory", "field.required","Event location should not be empty or null.");
    }
}

