/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.option;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Sebas663
 *
 */
public class OptionsController {

	private static final String AllowHeaders = "Access-Control-Allow-Headers";
	private static final String AllowMethods = "Access-Control-Allow-Methods";
	private static final String OK = "OK";
	private static final String RequestHeaders = "Access-Control-Request-Headers";
	private static final String RequestMethods = "Access-Control-Request-Method";

	public static Route options = (Request request, Response response) -> {

		String accessControlRequestHeaders = request.headers(RequestHeaders);
		if (accessControlRequestHeaders != null) {
			response.header(AllowHeaders, accessControlRequestHeaders);
		}

		String accessControlRequestMethod = request.headers(RequestMethods);
		if (accessControlRequestMethod != null) {
			response.header(AllowMethods, accessControlRequestMethod);
		}
		return OK;
	};

}
