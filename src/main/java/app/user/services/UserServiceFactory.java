/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.user.services;

/**
 * 
 * @author Sebas663
 *
 */
public class UserServiceFactory {

	/**
	 * @return
	 */
	public static UserService getUserService() {
		return UserServiceImpl.getInstance();
	}

}
