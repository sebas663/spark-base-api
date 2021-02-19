package app.user;

public class UserDaoFactory {

	/**
	 * @return
	 */
	public static UserDao getUserDao() {
		return UserDaoImpl.getInstance();
	}

}
