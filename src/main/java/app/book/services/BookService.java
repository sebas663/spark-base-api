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

import app.book.Book;
import app.book.BackendException;

/**
 * 
 * @author Sebas663
 *
 */
public interface BookService {

	public void addBook(Book book) throws BackendException;

	public List<Book> getAllBooks()throws BackendException;

	public Optional<Book> getBookByIsbn(String isbn) throws BackendException;

	public Optional<Book> updateBookByIsbn(Book book) throws BackendException;

	public Optional<Book> deleteBookByIsbn(String isbn)throws BackendException;

}
