package app.user;

public class User {

	private String username;
	private String salt;
	private String password;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String salt, String password) {
		super();
		this.username = username;
		this.salt = salt;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

}
