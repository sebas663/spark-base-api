/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.util;

import org.eclipse.jetty.http.HttpStatus;

import spark.Request;
import spark.Response;
import spark.Route;
/**
 * 
 * @author Sebas663
 *
 */
public class ResponseUtil {

	public static Route notFound = (Request request, Response response) -> {
		response.status(HttpStatus.NOT_FOUND_404);
		return new StandardResponse(HttpStatus.NOT_FOUND_404, HttpStatus.getMessage(HttpStatus.NOT_FOUND_404));
	};

	public static Route notAcceptable = (Request request, Response response) -> {
		response.status(HttpStatus.NOT_ACCEPTABLE_406);
		return new StandardResponse(HttpStatus.NOT_ACCEPTABLE_406,
				HttpStatus.getMessage(HttpStatus.NOT_ACCEPTABLE_406));
	};

	public static Route badRequest = (Request request, Response response) -> {
		response.status(HttpStatus.BAD_REQUEST_400);
		return new StandardResponse(HttpStatus.BAD_REQUEST_400, HttpStatus.getMessage(HttpStatus.BAD_REQUEST_400));
	};
}
