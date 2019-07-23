package com.macs.group6.daldiscussion.ValidatorRules;

public class ContainsDigitRule implements PasswordRule {
	
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

