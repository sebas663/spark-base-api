/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.book;

/**
 * @author Sebas663
 *
 */
public class BackendException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -285580354878966021L;

	/**
	 * @param message
	 */
	public BackendException(String message) {
		super(message);
	}

}
