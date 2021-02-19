/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.token;

import spark.Request;
/**
 * 
 * @author Sebas663
 *
 */
public interface TokenProvider {

	public String createToken(String username);

	public String getUsername(String token);

	public String resolveToken(Request req);

	public boolean validateToken(String token);
}
