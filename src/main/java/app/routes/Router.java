/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.routes;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.debug.DebugScreen.enableDebugScreen;

import app.option.OptionsController;
import app.swagger.SwaggerController;
import app.util.Filters;
import app.util.GsonUtil;
import app.util.ResponseUtil;
import spark.servlet.SparkApplication;

/**
 * @author Sebas663
 *
 */
public class Router implements SparkApplication {

	/*
	 * (non-Javadoc
	 * 
	 * @see spark.servlet.SparkApplication#init()
	 */
	@Override
	public void init() {

		// Configure Spark
		port(8080);

		enableDebugScreen();

		routes();
	}

	private void routes() {

		try {

			before(Filters.corsFilter);
			before(Filters.clientAcceptsJson);

			before(RoutesPaths.ENSURE_IS_AUTHENTICATED, Filters.ensureIsAuthemticated);

			// Scan classes with @Api annotation and add as routes
			RouteBuilder.setupRoutes(RoutePackages.APP_ROUTES_PACKAGES);

			get(RoutesPaths.SWAGGER, SwaggerController.swagger);

			options(RoutesPaths.ALL, OptionsController.options);
			get(RoutesPaths.ALL, ResponseUtil.notFound, GsonUtil::toJson);

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
