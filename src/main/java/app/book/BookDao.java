package app.book;

public interface BookDao {

	public boolean addBook(Book book);

	public Iterable<Book> getAllBooks();

	public Book getBookByIsbn(String isbn);

	public Book updateBookByIsbn(Book book) throws BookException;

	public boolean deleteBookByIsbn(String isbn);

	public Book getRandomBook();
}
