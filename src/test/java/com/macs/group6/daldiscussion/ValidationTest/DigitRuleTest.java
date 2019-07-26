package com.macs.group6.daldiscussion.ValidationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.macs.group6.daldiscussion.ValidatorRules.DigitRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitRuleTest {

	DigitRule DigitRule;

	@Before
	public void setUp() throws Exception {
		DigitRule = new DigitRule();
	}
	
	@After
	public void tearDown() {
		DigitRule = null;
	}

	@Test
	public void testPasswordNotContainsDigit() {
		assertFalse(DigitRule.isCriteriaSatisfied("PASSWORD"));

	}

	@Test
	public void testPasswordContainsDigit() {
		assertTrue(DigitRule.isCriteriaSatisfied("23PASSWORD19"));

	}

}
