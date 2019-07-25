package com.macs.group6.daldiscussion.Validator;


import com.macs.group6.daldiscussion.ValidatorRules.EmailValidator;
import com.macs.group6.daldiscussion.ValidatorRules.PasswordValidator;
import com.macs.group6.daldiscussion.ValidatorRules.StringRules;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.service.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class RegistrationValidator implements Validator {
	
	public static final String EMAIL = "email";
	public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
	public static final String PASSWORD = "password";
	public static final String NOTEMPTY = "NotEmpty";
	public static final String PASSWORD_CONFIRM = "passwordConfirm";
    
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        UserService userService = UserService.getInstance();


         

         if (StringRules.isNullOrEmpty(user.getFirstName())){
             errors.rejectValue(FIRST_NAME,ValidationCode.NOTEMPTY.getPropertyName(),"Enter valid First name" );
             return ;
         }

        if (StringRules.isNullOrEmpty(user.getLastName())){
            errors.rejectValue(LAST_NAME,ValidationCode.NOTEMPTY.getPropertyName(),"Enter valid Last name" );
            return ;
        }


        if (StringRules.isNullOrEmpty(user.getEmail())){
            errors.rejectValue(EMAIL,ValidationCode.NOTEMPTY.getPropertyName(), "Email cannot be null" );
            return ;
        }
        if (!EmailValidator.isValidEmailAddress(user.getEmail())) {
            errors.rejectValue(EMAIL,ValidationCode.EMIAL_NOT_VALID.getPropertyName(),"Email ID is not valid" );
            return;
        }

        if (StringRules.isNullOrEmpty(user.getPassword())){
            errors.rejectValue(PASSWORD,ValidationCode.NOTEMPTY.getPropertyName(),"Password cannot be Empty" );
            return ;
        }

        if (PasswordValidator.validatePasswordPolicy(user.getPassword())!=null){
            errors.rejectValue(PASSWORD,ValidationCode.PASSWORD_POLICY_DOES_NOT_SATISFY.getPropertyName(),"Enter a valid password" );
        }
    }
}
