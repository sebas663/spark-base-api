/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.token;
/**
 * 
 * @author Sebas663
 *
 */
public class TokenProperties {

	private String secretKey = "secret";

	// validity in milliseconds
//	60000; // 1m
//	300000; // 5m
	private long validityInMs = 300000;

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the validityInMs
	 */
	public long getValidityInMs() {
		return validityInMs;
	}

	/**
	 * @param validityInMs the validityInMs to set
	 */
	public void setValidityInMs(long validityInMs) {
		this.validityInMs = validityInMs;
	}

}
