package com.macs.group6.daldiscussion.Validator;


import com.macs.group6.daldiscussion.ValidatorRules.EmailValidator;
import com.macs.group6.daldiscussion.ValidatorRules.PasswordValidator;
import com.macs.group6.daldiscussion.ValidatorRules.StringRules;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.service.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Vivek Shah
 */

public class RegistrationValidator implements Validator {

    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PASSWORD = "password";
    public static final String NOTEMPTY = "NotEmpty";
    public static final String PASSWORD_CONFIRM = "passwordConfirm";
    private static RegistrationValidator instance;


    public static RegistrationValidator getInstance() {
        if (instance == null) {
            instance = new RegistrationValidator();
        }
        return instance;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;


        if (StringRules.isNullOrEmpty(user.getFirstName())){
            errors.rejectValue(FIRST_NAME,ValidationCode.NOTEMPTY.getPropertyName());
            return ;
        }


        if (StringRules.isNullOrEmpty(user.getLastName())){
            errors.rejectValue(LAST_NAME,ValidationCode.NOTEMPTY.getPropertyName() );
            return ;
        }


        if (StringRules.isNullOrEmpty(user.getEmail())){
            errors.rejectValue(EMAIL,ValidationCode.NOTEMPTY.getPropertyName() );
            return ;
        }
        if (!EmailValidator.isValidEmailAddress(user.getEmail())) {
            errors.rejectValue(EMAIL,ValidationCode.EMAIL_NOT_VALID.getPropertyName());
            return;
        }

        if (StringRules.isNullOrEmpty(user.getPassword())){
            errors.rejectValue(PASSWORD,ValidationCode.NOTEMPTY.getPropertyName());
            return ;
        }


        if (PasswordValidator.validatePasswordPolicy(user.getPassword())!=null){
            errors.rejectValue(PASSWORD,ValidationCode.PASSWORD_POLICY_DOES_NOT_SATISFY.getPropertyName() );
        }

        if (!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue(PASSWORD,ValidationCode.PASSWORD_DOES_NOT_MATCH.getPropertyName());
            return ;
        }

    }
}
