package com.macs.group6.daldiscussion.ValidationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.macs.group6.daldiscussion.ValidatorRules.LengthRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LengthRuleTest {
	
	LengthRule LengthRule;
	
	@Before
	public void setUp() throws Exception {
		LengthRule=new LengthRule();
	}
	
	@After
	public void tearDown() {
		LengthRule = null;
	}

	@Test
	public void testPasswordNotContainsRequiredLength() {
		assertFalse(LengthRule.isCriteriaSatisfied("Abc"));

	}

	@Test
	public void testPasswordContainsRequiredLength() {
		assertTrue(LengthRule.isCriteriaSatisfied("123PASSWORD98"));
	}
	
	

}
