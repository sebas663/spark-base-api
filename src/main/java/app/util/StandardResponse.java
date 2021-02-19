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
	private int status;
	private String message;
	private JsonElement data;

	public StandardResponse(int status) {
		this.status = status;
	}

	public StandardResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public StandardResponse(int status, JsonElement data) {
		this.status = status;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
