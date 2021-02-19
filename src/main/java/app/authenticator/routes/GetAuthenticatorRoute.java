/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.authenticator.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import app.authenticator.AuthenticatorController;
import app.util.StandardResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Sebas663
 *
 */
@Api
@Path(app.routes.RoutesPaths.AUTHENTICATE)
@Produces("application/json")
public class GetAuthenticatorRoute implements Route {

	/*
	 * (non-Javadoc
	 * 
	 * @see spark.Route#handle(spark.Request, spark.Response)
	 */
	@Override
	@GET
	@ApiOperation(value = "Get access token", nickname = "GetAuthenticatorRoute")
	@ApiImplicitParams({ //
			@ApiImplicitParam(required = true, dataType = "string", name = "auth", paramType = "header"), //
			@ApiImplicitParam(required = true, dataType = "app.user.routes.request.UserRequest", paramType = "body") //
	}) //
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Success", response = StandardResponse.class), //
			@ApiResponse(code = 400, message = "Invalid input data", response = StandardResponse.class), //
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardResponse.class), //
			@ApiResponse(code = 404, message = "User not found", response = StandardResponse.class) //
	})
	public Object handle(Request request, Response response) throws Exception {
		return AuthenticatorController.authenticate.handle(request, response);
	}
}
