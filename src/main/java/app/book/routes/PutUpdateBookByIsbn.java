/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.book.routes;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import app.book.BookController;
import app.routes.RoutesPaths;
import app.swagger.CustomApiResponses;
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
@Path(RoutesPaths.BOOKS + "{isbn}")
@Produces("application/json")
public class PutUpdateBookByIsbn implements Route {

	/*
	 * (non-Javadoc
	 * 
	 * @see spark.Route#handle(spark.Request, spark.Response)
	 */
	@Override
	@PUT
	@ApiOperation(value = "Update Bbok by isbn", nickname = "UpdateBookByIsbn")
	@ApiImplicitParams({ //
			@ApiImplicitParam(required = true, dataType = "string", name = "auth", paramType = "header"), //
			@ApiImplicitParam(name = "isbn", value = "ISBN", required = true, dataType = "long", paramType = "query"), //
			@ApiImplicitParam(required = true, dataType = "app.book.routes.request.BookRequest", paramType = "body") //
	}) //
	@CustomApiResponses
	public Object handle(Request request, Response response) throws Exception {
		return BookController.updateByIsbn.handle(request, response);
	}

}
