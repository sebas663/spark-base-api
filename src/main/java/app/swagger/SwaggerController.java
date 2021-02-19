/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.swagger;

import app.routes.RoutePackages;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Sebas663
 *
 */
public class SwaggerController {

	public static Route swagger = (Request request, Response response) -> {
		final String swaggerJson = SwaggerParser.getSwaggerJson(RoutePackages.APP_ROUTES_PACKAGES);
		return swaggerJson;
	};
}
