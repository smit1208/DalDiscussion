package com.macs.group6.daldiscussion.ValidationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.macs.group6.daldiscussion.ValidatorRules.LowerCaseRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LowerCaseRuleTest {

	LowerCaseRule LowerCaseRule;

	@Before
	public void setUp() throws Exception {
		LowerCaseRule = new LowerCaseRule();
	}
	@After
	public void tearDown() {
		LowerCaseRule = null;
	}

	@Test
	public void testPasswordContainsLowerCase() {
		assertTrue(LowerCaseRule.isCriteriaSatisfied("PasSWord"));
	}

	@Test
	public void testPasswordDoesNotContainsLowerCase() {
		assertFalse(LowerCaseRule.isCriteriaSatisfied("PASSWORD"));

	}

}
