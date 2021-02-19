/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.book;

import static app.Application.bookDao;
import static app.util.RequestUtil.clientAcceptsJson;
import static app.util.RequestUtil.getParamIsbn;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;

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

		if (clientAcceptsJson(request)) {
			String jsonBody = request.body();

			Book book = GsonUtil.fromJson(jsonBody, Book.class);

			bookDao.addBook(book);
			return new StandardResponse(HttpStatus.OK_200);
		}

		return ResponseUtil.notAcceptable.handle(request, response);
	};

	public static Route fetchAllBooks = (Request request, Response response) -> {

		if (clientAcceptsJson(request)) {

			Iterable<Book> books = bookDao.getAllBooks();
			return new StandardResponse(HttpStatus.OK_200, GsonUtil.toJsonTree(books));
		}

		return ResponseUtil.notAcceptable.handle(request, response);
	};

	public static Route fetchByIsbn = (Request request, Response response) -> {

		String isbn = getParamIsbn(request);

		if (StringUtils.isEmpty(isbn)) {
			return ResponseUtil.badRequest.handle(request, response);
		}

		if (clientAcceptsJson(request)) {

			Book book = bookDao.getBookByIsbn(getParamIsbn(request));
			return new StandardResponse(HttpStatus.OK_200, GsonUtil.toJsonTree(book));
		}

		return ResponseUtil.notAcceptable.handle(request, response);
	};

	public static Route updateByIsbn = (Request request, Response response) -> {
		String isbn = getParamIsbn(request);

		if (StringUtils.isEmpty(isbn)) {
			return ResponseUtil.badRequest.handle(request, response);
		}
		if (clientAcceptsJson(request)) {
			String jsonBody = request.body();

			Book book = GsonUtil.fromJson(jsonBody, Book.class);

			book.setIsbn(isbn);

			Book updatedBook = bookDao.updateBookByIsbn(book);
			return new StandardResponse(HttpStatus.OK_200, GsonUtil.toJsonTree(updatedBook));
		}

		return ResponseUtil.notAcceptable.handle(request, response);
	};

	public static Route deleteByIsbn = (Request request, Response response) -> {
		String isbn = getParamIsbn(request);

		if (StringUtils.isEmpty(isbn)) {
			return ResponseUtil.badRequest.handle(request, response);
		}

		if (clientAcceptsJson(request)) {

			boolean updatedBook = bookDao.deleteBookByIsbn(isbn);
			if (updatedBook) {
				return new StandardResponse(HttpStatus.OK_200);
			}
			return new StandardResponse(HttpStatus.OK_200);
		}

		return ResponseUtil.notAcceptable.handle(request, response);
	};

}
