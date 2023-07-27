package com.group6.commune.Validators;

import com.group6.commune.Model.UserInterests;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class UserInterestsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserInterests.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "interestIds", "field.required", "Interest Ids should not be null or empty.");

        UserInterests userInterests = (UserInterests) target;

        if (userInterests.getUserId() <= 0) {
            errors.rejectValue("userId", "field.required", "User id should be greater than zero.");
        }

        List<Integer> interestIds = userInterests.getInterestIds();
        if (interestIds != null) {
            for (int i = 0; i < interestIds.size(); i++) {
                Integer interestId = interestIds.get(i);
                if (interestId <= 0) {
                    errors.rejectValue("interestIds[" + i + "]", "field.required", "Interest id should be greater than zero.");
                }
            }
        }
    }
}
