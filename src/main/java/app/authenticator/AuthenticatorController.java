package app.authenticator;

import static app.Application.userDao;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.mindrot.jbcrypt.BCrypt;

import app.token.Token;
import app.token.TokenProvider;
import app.token.TokenProviderFactory;
import app.user.User;
import app.util.GsonUtil;
import app.util.ResponseUtil;
import app.util.StandardResponse;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthenticatorController {

	private static TokenProvider tokenProvider = TokenProviderFactory.getTokenProvider();

	public static Route authenticate = (Request request, Response response) -> {

		String jsonBody = request.body();
		User newUser = GsonUtil.fromJson(jsonBody, User.class);

		String username = newUser.getUsername();
		String password = newUser.getPassword();

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {

			return ResponseUtil.badRequest.handle(request, response);
		}

		if (!authenticate(username, password)) {

			return new StandardResponse(HttpStatus.OK_200, "Wrong user or password");
		}

		String token = tokenProvider.createToken(username);
		return new StandardResponse(HttpStatus.OK_200, GsonUtil.toJsonTree(new Token(token)));

	};

	private static boolean authenticate(String username, String password) {
		if (username.isEmpty() || password.isEmpty()) {
			return false;
		}
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			return false;
		}
		String hashedPassword = BCrypt.hashpw(password, user.getSalt());
		return hashedPassword.equals(user.getPassword());
	}

}
