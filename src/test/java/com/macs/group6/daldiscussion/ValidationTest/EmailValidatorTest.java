package com.macs.group6.daldiscussion.ValidationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.macs.group6.daldiscussion.ValidatorRules.EmailValidator;
import org.junit.Test;


public class EmailValidatorTest {
	
	@Test
	public void testEmailisEmpty() {
		String emailIsEmpty=null;
		assertFalse(EmailValidator.isValidEmailAddress(emailIsEmpty));
	}
	
	
	@Test
	public void testEmailWithNoDomain() {
		String emailWithNoDomain="vivek.shah";
		assertFalse(EmailValidator.isValidEmailAddress(emailWithNoDomain));
	}
	
	@Test
	public void testEmailWithNoAtTheRateSymbol() {
		String emailWithNoAtTheRateSymbol="vivek.shah.gmail.com";
		assertFalse(EmailValidator.isValidEmailAddress(emailWithNoAtTheRateSymbol));
	}
	
	@Test
	public void testEmailWithMissingPeriod() {
		String emailWithNoDot="vivek.shah@gmailcom";
		assertFalse(EmailValidator.isValidEmailAddress(emailWithNoDot));
	}
	
	@Test
	public void testValidEmail() {
		String emailWithNoAtTheRateSymbol="vivek.shah@gmail.com";
		assertTrue(EmailValidator.isValidEmailAddress(emailWithNoAtTheRateSymbol));
	}

	

}
