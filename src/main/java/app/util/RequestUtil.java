/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.util;

import spark.*;

/**
 * 
 * @author Sebas663
 *
 */
public class RequestUtil {

	public static String getParam(Request request, String param) {
		return request.params(param);
	}

	public static String getQueryParam(Request request, String queryParam) {
		return request.queryParams(queryParam);
	}

	public static String getSessionAttribute(Request request, String attribute) {
		return request.session().attribute(attribute);
	}

	public static boolean clientAcceptsJson(Request request) {
		String accept = request.headers("Accept");
		return accept != null && accept.contains("application/json");
	}

}
