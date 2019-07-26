package com.macs.group6.daldiscussion.ValidationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.macs.group6.daldiscussion.ValidatorRules.StringRules;
import org.junit.Test;

public class StringRulesTest {

	@Test
	public void testStringIsNull() {
		boolean stringIsNull = StringRules.isNullOrEmpty(null);
		assertTrue(stringIsNull);
	}

	@Test
	public void testStringIsEmpty() {
		boolean stringIsEmpty = StringRules.isNullOrEmpty("");
		assertTrue(stringIsEmpty);
	}

	@Test
	public void testStringIsNotEmpty() {
		boolean stringIsNotEmpty = StringRules.isNullOrEmpty("vivek");
		assertFalse(stringIsNotEmpty);
	}

	@Test
	public void testStringIsWhitespace() {
		boolean stringIsWhitespace = StringRules.isNullOrWhiteSpace("    ");
		assertTrue(stringIsWhitespace);
	}

	@Test
	public void testStringIsNotWhitespace() {
		boolean stringIsWhitespace = StringRules.isNullOrWhiteSpace("vivek");
		assertFalse(stringIsWhitespace);

	}

	@Test
	public void testStringIsNullOrWhitespace() {
		boolean stringIsWhitespace = StringRules.isNullOrWhiteSpace(null);
		assertTrue(stringIsWhitespace);
	}

	@Test
	public void testStringIsNotNullorWhitespace() {
		boolean stringIsWhitespace = StringRules.isNullOrWhiteSpace("vivek");
		assertFalse(stringIsWhitespace);

	}

}
