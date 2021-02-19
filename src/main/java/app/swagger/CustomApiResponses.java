/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import app.util.StandardResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Sebas663
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = { //
		@ApiResponse(code = 200, message = "Success", response = StandardResponse.class), //
		@ApiResponse(code = 400, message = "Invalid input data", response = StandardResponse.class), //
		@ApiResponse(code = 401, message = "Unauthorized", response = StandardResponse.class), //
		@ApiResponse(code = 404, message = "User not found", response = StandardResponse.class) //
})
public @interface CustomApiResponses {

}
