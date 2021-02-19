package app.token;

import spark.Request;

public interface TokenProvider {

	public String createToken(String username);

	public String getUsername(String token);

	public String resolveToken(Request req);

	public boolean validateToken(String token);
}
