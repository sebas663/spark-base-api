/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.user;
/**
 * 
 * @author Sebas663
 *
 */
public interface UserDao {

	public User getUserByUsername(String username);

	public Iterable<String> getAllUserNames();

}
