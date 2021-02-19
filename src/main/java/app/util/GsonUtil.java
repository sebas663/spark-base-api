/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * @author Sebas663
 *
 */
public class GsonUtil {

	private static GsonUtil instance;

	private GsonUtil() {
		this.gson = new GsonBuilder().create();
	}

	public static synchronized GsonUtil getInstance() {
		if (instance == null) {
			synchronized (GsonUtil.class) {
				if (instance == null) {
					instance = new GsonUtil();
				}
			}
		}
		return instance;
	}

	private Gson gson;

	public static Gson getGson() {
		return getInstance().gson;
	}

	/**
	 * @param jsonBody
	 * @param class1
	 * @return
	 * @return
	 */
	public static <T> T fromJson(String jsonBody, Class<T> clazz) {
		return getGson().fromJson(jsonBody, clazz);
	}

	/**
	 * @param token
	 * @return
	 */
	public static <T> JsonElement toJsonTree(T t) {
		return getGson().toJsonTree(t);
	}
	
	/**
	 * @param token
	 * @return
	 */
	public static <T> String toJson(T t) {
		return getGson().toJson(t);
	}
}
