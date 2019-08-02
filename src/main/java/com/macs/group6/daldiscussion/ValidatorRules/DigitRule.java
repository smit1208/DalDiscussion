package com.macs.group6.daldiscussion.ValidatorRules;

/**
 * @author Vivek Shah
 */

public class DigitRule implements PasswordRule {
	
    @Override
    public boolean isCriteriaSatisfied(String password) {
        for(int i = 0; i < password.length(); i++) {
            if(Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}

