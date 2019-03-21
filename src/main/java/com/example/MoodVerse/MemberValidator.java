package com.example.MoodVerse;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator {

    @Override
    public boolean supports(Class clazz){
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors error){

        ValidationUtils.rejectIfEmptyOrWhitespace(error, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "lastName", "lastName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "password.empty");
    }

}
