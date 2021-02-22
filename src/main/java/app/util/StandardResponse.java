/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.util;

import com.google.gson.JsonElement;

/**
 * @author Sebas663
 *
 */
public class StandardResponse {
	private int code;
	private String message;
	private JsonElement data;

	public StandardResponse(int code) {
		this.code = code;
	}

	public StandardResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public StandardResponse(int code, JsonElement data) {
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JsonElement getData() {
		return data;
	}

	public void setData(JsonElement data) {
		this.data = data;
	}
}
