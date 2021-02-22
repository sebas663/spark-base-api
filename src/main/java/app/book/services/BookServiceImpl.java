/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.book.services;

import java.util.List;
import java.util.Optional;

import app.book.BackendException;
import app.book.Book;
import app.book.daos.BookDao;
import app.book.daos.BookDaoFactory;

/**
 * 
 * @author Sebas663
 *
 */
public class BookServiceImpl implements BookService {

	private static BookServiceImpl instance;
	private static BookDao bookDao;

	private BookServiceImpl() {
		bookDao = BookDaoFactory.getBookDao();
	}

	public static synchronized BookServiceImpl getInstance() {
		if (instance == null) {
			synchronized (BookServiceImpl.class) {
				if (instance == null) {
					instance = new BookServiceImpl();
				}
			}
		}
		return instance;
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookService#addBook(app.book.Book)
	 */
	@Override
	public void addBook(Book book) throws BackendException {
		try {
			if (book.getIsbn() == null) {
				throw new BackendException("Isbn cannot be blank");
			}
			bookDao.addBook(book);
		} catch (BackendException e) {
			throw new BackendException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookService#getAllBooks()
	 */
	@Override
	public List<Book> getAllBooks() throws BackendException {
		try {

			return bookDao.getAllBooks();
		} catch (BackendException e) {
			throw new BackendException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookService#getBookByIsbn(java.lang.String)
	 */
	@Override
	public Optional<Book> getBookByIsbn(String isbn) throws BackendException {
		try {
			if (isbn == null) {
				throw new BackendException("Isbn cannot be blank");
			}
			return bookDao.getBookByIsbn(isbn);
		} catch (BackendException e) {
			throw new BackendException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookService#updateBookByIsbn(app.book.Book)
	 */
	@Override
	public Optional<Book> updateBookByIsbn(Book book) throws BackendException {
		try {
			if (book.getIsbn() == null) {
				throw new BackendException("Isbn cannot be blank");
			}

			Optional<Book> toEditOp = getBookByIsbn(book.getIsbn());

			if (!toEditOp.isPresent()) {
				throw new BackendException("Book not found");
			}

			Book toEdit = toEditOp.get();

			if (book.getAuthor() != null) {
				toEdit.setAuthor(book.getAuthor());
			}

			if (book.getTitle() != null) {
				toEdit.setTitle(book.getTitle());
			}

			return bookDao.updateBookByIsbn(toEdit);

		} catch (Exception ex) {
			throw new BackendException(ex.getMessage());
		}
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookService#deleteBookByIsbn(java.lang.String)
	 */
	@Override
	public Optional<Book> deleteBookByIsbn(String isbn) throws BackendException {
		try {
			if (isbn == null) {
				throw new BackendException("Isbn cannot be blank");
			}
			return bookDao.deleteBookByIsbn(isbn);
		} catch (BackendException e) {
			throw new BackendException(e.getMessage());
		}
	}
}
