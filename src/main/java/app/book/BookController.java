/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.book;

import static app.init.Application.bookDao;
import static app.util.RequestUtil.getParamIsbn;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import app.constants.ResponseCodes;
import app.util.GsonUtil;
import app.util.ResponseUtil;
import app.util.StandardResponse;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * 
 * @author Sebas663
 *
 */
public class BookController {

	public static Route createBook = (Request request, Response response) -> {
		try {
			String jsonBody = request.body();

			Book book = GsonUtil.fromJson(jsonBody, Book.class);
			bookDao.addBook(book);

			return new StandardResponse(ResponseCodes.OK);
		} catch (BackendException e) {
			return new StandardResponse(ResponseCodes.NOT_OK, e.getMessage());
		}
	};

	public static Route fetchAllBooks = (Request request, Response response) -> {
		try {
			Iterable<Book> books = bookDao.getAllBooks();

			return new StandardResponse(ResponseCodes.OK, GsonUtil.toJsonTree(books));
		} catch (BackendException e) {
			return new StandardResponse(ResponseCodes.NOT_OK, e.getMessage());
		}
	};

	public static Route fetchByIsbn = (Request request, Response response) -> {

		String isbn = getParamIsbn(request);

		if (StringUtils.isEmpty(isbn)) {
			return ResponseUtil.badRequest.handle(request, response);
		}

		try {
			Optional<Book> book = bookDao.getBookByIsbn(getParamIsbn(request));

			return ResponseUtil.returnStandardResponseWithData(book);
		} catch (BackendException e) {
			return new StandardResponse(ResponseCodes.NOT_OK, e.getMessage());
		}
	};

	public static Route updateByIsbn = (Request request, Response response) -> {
		String isbn = getParamIsbn(request);

		if (StringUtils.isEmpty(isbn)) {
			return ResponseUtil.badRequest.handle(request, response);
		}

		try {
			String jsonBody = request.body();

			Book book = GsonUtil.fromJson(jsonBody, Book.class);

			book.setIsbn(isbn);

			Optional<Book> updatedBook = bookDao.updateBookByIsbn(book);

			return ResponseUtil.returnStandardResponseWithData(updatedBook);
		} catch (BackendException e) {
			return new StandardResponse(ResponseCodes.NOT_OK, e.getMessage());
		}
	};

	public static Route deleteByIsbn = (Request request, Response response) -> {
		String isbn = getParamIsbn(request);

		if (StringUtils.isEmpty(isbn)) {
			return ResponseUtil.badRequest.handle(request, response);
		}

		try {
			Optional<Book> deleteBook = bookDao.deleteBookByIsbn(isbn);

			return ResponseUtil.returnStandardResponseWithData(deleteBook);
		} catch (BackendException e) {
			return new StandardResponse(ResponseCodes.NOT_OK, e.getMessage());
		}
	};

}
