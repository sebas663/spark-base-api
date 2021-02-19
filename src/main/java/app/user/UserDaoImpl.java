/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * 
 * @author Sebas663
 *
 */
public class UserDaoImpl implements UserDao {

	private static UserDaoImpl instance;

	private UserDaoImpl() {
	}

	public static synchronized UserDaoImpl getInstance() {
		if (instance == null) {
			synchronized (UserDaoImpl.class) {
				if (instance == null) {
					instance = new UserDaoImpl();
				}
			}
		}
		return instance;
	}

	private final List<User> users = Arrays.asList(
			// Username Salt for hash Hashed password (the password is "password" for all
			// users)
			new User("perwendel", "$2a$10$h.dl5J86rGH7I8bD9bZeZe",
					"$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO"),
			new User("davidase", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe",
					"$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy"),
			new User("federico", "$2a$10$E3DgchtVry3qlYlzJCsyxe",
					"$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2"));

	/*
	 * (non-Javadoc
	 * 
	 * @see app.user.UserDao#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) {
		return users.stream().filter(b -> b.getUsername().equals(username)).findFirst().orElse(null);
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.user.UserDao#getAllUserNames()
	 */
	@Override
	public Iterable<String> getAllUserNames() {
		return users.stream().map(User::getUsername).collect(Collectors.toList());
	}

}
