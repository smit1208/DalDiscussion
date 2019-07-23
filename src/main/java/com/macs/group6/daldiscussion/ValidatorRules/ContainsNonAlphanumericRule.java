package com.macs.group6.daldiscussion.ValidatorRules;

public class ContainsNonAlphanumericRule implements PasswordRule {
    @Override
    public boolean isCriteriaSatisfied(String password) {
        for(int i = 0; i < password.length(); i++) {
            if(!Character.isLetterOrDigit(password.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
