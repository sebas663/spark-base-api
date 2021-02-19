package app;

import app.book.BookDao;
import app.book.BookDaoFactory;
import app.routes.Router;
import app.user.UserDao;
import app.user.UserDaoFactory;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(host = "localhost:8080", //
		info = @Info(description = "Randoms API", //
				version = "V1.0", //
				title = "Some random api for testing", //
				contact = @Contact(name = "Serol", url = "https://sebas663.com")), //
		schemes = { SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS }, //
		consumes = { "application/json" }, //
		produces = { "application/json" }, //
		tags = { @Tag(name = "swagger") })
public class Application {

	// Declare dependencies
	public static BookDao bookDao;
	public static UserDao userDao;

	public static void main(String[] args) {
		// Instantiate your dependencies
		bookDao = BookDaoFactory.getBookDao();
		userDao = UserDaoFactory.getUserDao();

		Router router = new Router();
		router.init();
	}

}
