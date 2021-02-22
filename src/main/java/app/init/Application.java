/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.init;

import app.book.daos.BookDao;
import app.book.daos.BookDaoFactory;
import app.routes.Router;
import app.user.services.UserService;
import app.user.services.UserServiceFactory;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * 
 * @author Sebas663
 *
 */
@SwaggerDefinition(host = "localhost:8080", //
		info = @Info(description = "Randoms API", //
				version = "V1.0", //
				title = "Some random api for testing", //
				contact = @Contact(name = "sebas663", url = "https://sebas663.com")), //
		schemes = { SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS }, //
		consumes = { "application/json" }, //
		produces = { "application/json" }, //
		tags = { @Tag(name = "swagger") })
public class Application {

	// Declare dependencies
	public static BookDao bookDao;
	public static UserService userService;

	public static void main(String[] args) {
		// Instantiate your dependencies
		userService = UserServiceFactory.getUserService();
		bookDao = BookDaoFactory.getBookDao();
		

		Router router = new Router();
		router.init();
	}

}
