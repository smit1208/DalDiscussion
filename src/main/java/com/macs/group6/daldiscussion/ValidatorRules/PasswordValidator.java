package com.macs.group6.daldiscussion.ValidatorRules;

import java.util.stream.Stream;

import com.macs.group6.daldiscussion.Validator.ValidationCode;


public class PasswordValidator {

	private PasswordValidator() {

	}

	public static String validatePasswordPolicy(String newPassword) {
		PasswordRule lengthRule = new LengthRule();
		if (!lengthRule.isCriteriaSatisfied(newPassword)) {
			return ValidationCode.PASSWORD_SIZE_POLICY.getPropertyName();
		}
		PasswordRule[] additionalRules = { new DigitRule(), new LowerCaseRule(),
				new NonAlphanumericRule(), new UpperCaseRule(), };
		long satisfiedRulesCount = Stream.of(additionalRules).filter(r -> r.isCriteriaSatisfied(newPassword)).count();
		if (satisfiedRulesCount < 3) {
			return ValidationCode.PASSWORD_POLICY_DOES_NOT_SATISFY.getPropertyName();
		}
		return null;
	}

}
