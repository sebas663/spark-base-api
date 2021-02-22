/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.user.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import app.routes.RoutesPaths;
import app.swagger.CustomApiResponses;
import app.user.UserAuthenticationController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Sebas663
 *
 */
@Api
@Path(RoutesPaths.AUTHENTICATE)
@Produces("application/json")
public class GetUserAuthenticationRoute implements Route {

	/*
	 * (non-Javadoc
	 * 
	 * @see spark.Route#handle(spark.Request, spark.Response)
	 */
	@Override
	@GET
	@ApiOperation(value = "Get access token", nickname = "GetUserAuthenticationRoute")
	@ApiImplicitParams({ //
			@ApiImplicitParam(required = true, dataType = "app.user.routes.request.UserRequest", paramType = "body") //
	}) //
	@CustomApiResponses
	public Object handle(Request request, Response response) throws Exception {
		return UserAuthenticationController.authenticate.handle(request, response);
	}
}
