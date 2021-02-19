/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.user.services;

import java.util.List;

import app.user.User;
import app.user.daos.UserDao;
import app.user.daos.UserDaoFactory;

/**
 * 
 * @author Sebas663
 *
 */
public class UserServiceImpl implements UserService {

	private static UserServiceImpl instance;
	public static UserDao userDao;

	private UserServiceImpl() {
		userDao = UserDaoFactory.getUserDao();
	}

	public static synchronized UserServiceImpl getInstance() {
		if (instance == null) {
			synchronized (UserServiceImpl.class) {
				if (instance == null) {
					instance = new UserServiceImpl();
				}
			}
		}
		return instance;
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.user.UserDao#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.user.UserDao#getAllUserNames()
	 */
	@Override
	public List<String> getAllUserNames() {
		return userDao.getAllUserNames();
	}

}
