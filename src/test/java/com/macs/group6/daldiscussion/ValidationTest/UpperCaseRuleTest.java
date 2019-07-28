package com.macs.group6.daldiscussion.ValidationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.macs.group6.daldiscussion.ValidatorRules.UpperCaseRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UpperCaseRuleTest {
	
	UpperCaseRule UpperCaseRule;

	@Before
	public void setUp() throws Exception {
		UpperCaseRule=new UpperCaseRule();
	}
	
	@After
	public void tearDown() {
		UpperCaseRule = null;
	}
	
	@Test
	public void tesContainsUpperCaseRule() {
		assertTrue(UpperCaseRule.isCriteriaSatisfied("passWoRd"));

	}

	@Test
	public void testDoesNotSatisfyContainsUpperCaseRule() {
		assertFalse(UpperCaseRule.isCriteriaSatisfied("password"));

	}

}
