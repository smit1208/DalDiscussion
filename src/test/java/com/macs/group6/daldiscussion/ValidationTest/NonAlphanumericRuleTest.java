package com.macs.group6.daldiscussion.ValidationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.macs.group6.daldiscussion.ValidatorRules.NonAlphanumericRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NonAlphanumericRuleTest {
	

	NonAlphanumericRule NonAlphanumericRule;
	
	@Before
	public void setUp() throws Exception {
		NonAlphanumericRule=new NonAlphanumericRule();
	}
	
	@After
	public void tearDown() {
		NonAlphanumericRule = null;
	}
	
	@Test
	public void testPasswordContainsOnlyAlphabets() {
		assertTrue(NonAlphanumericRule.isCriteriaSatisfied("vivek"));
	}
	
	@Test
	public void testPasswordContainsOnlyNumbers() {
		assertTrue(NonAlphanumericRule.isCriteriaSatisfied("112323"));
	}
	
	@Test
	public void testPasswordContainsAlphaNumeric() {
		assertTrue(NonAlphanumericRule.isCriteriaSatisfied("123344ASSDKJS"));
	}


	@Test
	public void testPasswordContainsNonAlphaNumeric() {
		assertFalse(NonAlphanumericRule.isCriteriaSatisfied("#$$#%$%$%"));
	}


}
