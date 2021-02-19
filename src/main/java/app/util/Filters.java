package app.util;

import static spark.Spark.halt;

import org.eclipse.jetty.http.HttpStatus;

import app.token.TokenProvider;
import app.token.TokenProviderFactory;
import spark.Filter;
import spark.Request;
import spark.Response;

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

	private static void handleHalt() {
		halt(HttpStatus.UNAUTHORIZED_401, HttpStatus.getMessage(HttpStatus.UNAUTHORIZED_401));
	}

}
