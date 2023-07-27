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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required","Interest name should not be empty or null.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "field.required","Interest category should not be empty or null.");

        Interest interest = (Interest) target;
        if (interest.getInterestId() <=0){
            errors.rejectValue("interest_id", "field.required","Interest id should be greater than zero.");
        }
     }
}

