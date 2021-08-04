package com.modularbank.payments.api.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @description This Class is used generate random string values for Id numbers
 *
 */
public class RandonIdGenerator {

	public String randomIdGen() {

		String genratedString = RandomStringUtils.randomAlphanumeric(8);

		return genratedString;

	}
	
	public String randomAlphaGen() {
		
		String genratedAlphaString = RandomStringUtils.randomAlphabetic(6);
		
		return genratedAlphaString;
		
	}

}
