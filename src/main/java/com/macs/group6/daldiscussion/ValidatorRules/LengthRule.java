package com.macs.group6.daldiscussion.ValidatorRules;

import org.apache.log4j.Logger;



public class LengthRule implements PasswordRule {
	
	final static Logger logger = Logger.getLogger(LengthRule.class);
	
    @Override
    public boolean isCriteriaSatisfied(String password) {
    	try
    	{
         return password.length() >= 6 && password.length() <= 15;
    	}catch(NumberFormatException e)
    	{
    		logger.error("could not parse  password size Min and Max length : " );
    		e.printStackTrace();
    	}
		return false;
    }
}
