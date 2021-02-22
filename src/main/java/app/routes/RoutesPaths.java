package app.routes;

public class RoutesPaths {

	public static final String AUTHENTICATE = "/authenticate/";
	public static final String SWAGGER = "/swagger";
	public static final String ENSURE_IS_AUTHENTICATED = "/v1/*";
	public static final String ALL = "/*";

	private static final String BASE = "/v1/";
	public static final String UNAUTHORIZED = BASE + "unauthorized/";
	public static final String BOOKS = BASE + "books/";

}
