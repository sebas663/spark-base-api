/* 
 * Copyright 2021 Sebas663. 
 * 
 * This software component is the intellectual property of Sebas663 S.A. 
 * You are not allowed to use, change or distribute it without express written consent from its author. 
 * 
 * https://www.sebas663.com
 */
package app.book;
/**
 * 
 * @author Sebas663
 *
 */
public interface BookDao {

	public boolean addBook(Book book);

	public Iterable<Book> getAllBooks();

	public Book getBookByIsbn(String isbn);

	public Book updateBookByIsbn(Book book) throws BookException;

	public boolean deleteBookByIsbn(String isbn);

	public Book getRandomBook();
}
