package app.util;

import org.eclipse.jetty.http.HttpStatus;

import spark.Request;
import spark.Response;
import spark.Route;

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
