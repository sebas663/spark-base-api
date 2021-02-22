/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.book.daos;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;
import java.util.Optional;

import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import app.book.BackendException;
import app.book.Book;
import app.mongo.MongoConnectionHandler;
import app.mongo.MongoUtil;

/**
 * 
 * @author Sebas663
 *
 */
public class BookDaoImpl implements BookDao {

	private static BookDaoImpl instance;

	private MongoCollection<Book> collection;

	private BookDaoImpl() {
		collection = MongoConnectionHandler.getInstance().getCollection(Book.class);
		collection.createIndex(Indexes.ascending("isbn"), new IndexOptions().unique(true));
	}

	public static synchronized BookDaoImpl getInstance() {
		if (instance == null) {
			synchronized (BookDaoImpl.class) {
				if (instance == null) {
					instance = new BookDaoImpl();
				}
			}
		}
		return instance;
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#addBook(app.book.Book)
	 */
	@Override
	public void addBook(Book book) throws BackendException {
		try {
			collection.insertOne(book);
		} catch (MongoException e) {
			throw new BackendException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#getAllBooks()
	 */
	@Override
	public List<Book> getAllBooks() throws BackendException {
		try {
			FindIterable<Book> listDocuments = collection.find();

			return MongoUtil.getInstance().getListFromFindIterable(listDocuments);
		} catch (MongoException e) {
			throw new BackendException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#getBookByIsbn(java.lang.String)
	 */
	@Override
	public Optional<Book> getBookByIsbn(String isbn) throws BackendException {
		try {
			return Optional.ofNullable(collection.find(getFilterByISBN(isbn)).first());
		} catch (MongoException e) {
			throw new BackendException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#updateBookByIsbn(app.book.Book)
	 */
	@Override
	public Optional<Book> updateBookByIsbn(Book book) throws BackendException {
		try {
			return Optional.ofNullable(collection.findOneAndReplace(getFilterByISBN(book.getIsbn()), book,
					MongoUtil.getInstance().getReturnDocAfterReplace()));

		} catch (Exception ex) {
			throw new BackendException(ex.getMessage());
		}
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#deleteBookByIsbn(java.lang.String)
	 */
	@Override
	public Optional<Book> deleteBookByIsbn(String isbn) throws BackendException {
		try {
			return Optional.ofNullable(collection.findOneAndDelete(getFilterByISBN(isbn)));
		} catch (MongoException e) {
			throw new BackendException(e.getMessage());
		}
	}

	private Bson getFilterByISBN(String isbn) {
		return eq("isbn", isbn);
	}

}
