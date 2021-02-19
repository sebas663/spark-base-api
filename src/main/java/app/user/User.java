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
public class User {

	private String username;
	private String salt;
	private String password;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String salt, String password) {
		super();
		this.username = username;
		this.salt = salt;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

}
