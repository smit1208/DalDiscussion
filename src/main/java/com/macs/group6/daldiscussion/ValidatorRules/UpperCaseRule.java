package com.macs.group6.daldiscussion.ValidatorRules;

/**
 * @author Vivek Shah
 */

public class UpperCaseRule implements PasswordRule {
    @Override
    public boolean isCriteriaSatisfied(String password) {
        for(int i = 0; i < password.length(); i++) {
            if(Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}
