/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.util;

import static app.util.RequestUtil.clientAcceptsJson;
import static spark.Spark.halt;

import org.eclipse.jetty.http.HttpStatus;

import app.token.TokenProvider;
import app.token.TokenProviderFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * 
 * @author Sebas663
 *
 */
public class Filters {

	private static TokenProvider tokenProvider = TokenProviderFactory.getTokenProvider();
	private static final String headerAllowHeaders = "Access-Control-Allow-Headers";
	private static final String headerAllowOrigin = "Access-Control-Allow-Origin";
	private static final String headerRequestMethod = "Access-Control-Request-Method";
	private static final String wildcard = "*";

	public static Filter ensureIsAuthemticated = (Request request, Response response) -> {

		String token = tokenProvider.resolveToken(request);

		try {
			if (token == null || !tokenProvider.validateToken(token)) {

				handleHalt();
			}

		} catch (IllegalArgumentException e) {
			handleHalt();
		}

	};

	public static Filter corsFilter = (Request request, Response response) -> {
		response.header(headerAllowOrigin, wildcard);
		response.header(headerRequestMethod, wildcard);
		response.header(headerAllowHeaders, wildcard);

	};

	public static Filter clientAcceptsJson = (Request request, Response response) -> {
		
		if (!clientAcceptsJson(request)) {

			response.status(HttpStatus.NOT_ACCEPTABLE_406);

			halt(HttpStatus.NOT_ACCEPTABLE_406, HttpStatus.getMessage(HttpStatus.NOT_ACCEPTABLE_406));
		}
	};

	private static void handleHalt() {
		halt(HttpStatus.UNAUTHORIZED_401, HttpStatus.getMessage(HttpStatus.UNAUTHORIZED_401));
	}

}
