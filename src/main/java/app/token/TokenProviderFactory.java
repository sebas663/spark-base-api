package app.token;

public class TokenProviderFactory {

	/**
	 * @return
	 */
	public static TokenProvider getTokenProvider() {
		return new TokenProviderJwt();
	}
}
