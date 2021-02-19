/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.swagger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Swagger;

/**
 * @author Sebas663
 *
 */
public class SwaggerParser {

	public static String getSwaggerJson(List<String> packagesNames) throws JsonProcessingException {
		return swaggerToJson(getSwagger(packagesNames));
	}

	public static String getSwaggerJson(String packageName) throws JsonProcessingException {
		Swagger swagger = getSwagger(packageName);
		String json = swaggerToJson(swagger);
		return json;
	}

	public static Swagger getSwagger(List<String> packageNames) {
		Reflections reflections = new Reflections(packageNames);
		BeanConfig beanConfig = new BeanConfig();
		Set<String> resourcePackages = new HashSet<>();
		for (String packageName : packageNames) {
			resourcePackages.add(packageName);
		}

		beanConfig.setResourcePackage(String.join(",", resourcePackages));
		beanConfig.setScan(true);
		beanConfig.scanAndRead();
		beanConfig.setPrettyPrint(true);

		Swagger swagger = beanConfig.getSwagger();

		Reader reader = new Reader(swagger);

		Set<Class<?>> apiClasses = reflections.getTypesAnnotatedWith(Api.class);
		return reader.read(apiClasses);
	}

	public static Swagger getSwagger(String packageName) {
		Reflections reflections = new Reflections(packageName);
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setResourcePackage(packageName);
		beanConfig.setScan(true);
		beanConfig.scanAndRead();
		beanConfig.setPrettyPrint(true);

		Swagger swagger = beanConfig.getSwagger();

		Reader reader = new Reader(swagger);

		Set<Class<?>> apiClasses = reflections.getTypesAnnotatedWith(Api.class);
		return reader.read(apiClasses);
	}

	public static String swaggerToJson(Swagger swagger) throws JsonProcessingException {
		return getJson(swagger);
	}

	/**
	 * @param swagger
	 * @return
	 * @throws JsonProcessingException
	 */
	private static String getJson(Object value) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		String json = objectMapper.writeValueAsString(value);
		return json;
	}

}
