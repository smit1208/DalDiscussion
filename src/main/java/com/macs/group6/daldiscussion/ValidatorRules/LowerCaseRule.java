package com.macs.group6.daldiscussion.ValidatorRules;

public class LowerCaseRule implements PasswordRule{
	
	 @Override
	    public boolean isCriteriaSatisfied(String password) {
	        for(int i = 0; i < password.length(); i++) {
	            if(Character.isLowerCase(password.charAt(i))) {
	                return true;
	            }
	        }
	        return false;
	    }

}
