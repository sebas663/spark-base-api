package app.token;

public class TokenProperties {

	private String secretKey = "secret";

	// validity in milliseconds
//	60000; // 1m
//	300000; // 5m
	private long validityInMs = 300000;

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the validityInMs
	 */
	public long getValidityInMs() {
		return validityInMs;
	}

	/**
	 * @param validityInMs the validityInMs to set
	 */
	public void setValidityInMs(long validityInMs) {
		this.validityInMs = validityInMs;
	}

}
