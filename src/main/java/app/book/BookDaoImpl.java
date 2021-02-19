package app.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BookDaoImpl implements BookDao {

	private static BookDaoImpl instance;

	private BookDaoImpl() {
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

	private List<Book> books = new ArrayList<>(Arrays.asList(new Book("Moby Dick", "Herman Melville", "9789583001215"),
			new Book("A Christmas Carol", "Charles Dickens", "9780141324524"),
			new Book("Pride and Prejudice", "Jane Austen", "9781936594290"),
			new Book("The Fellowship of The Ring", "J. R. R. Tolkien", "0007171978"),
			new Book("Harry Potter", "J. K. Rowling", "0747532699"),
			new Book("War and Peace", "Leo Tolstoy", "9780060798871"),
			new Book("Don Quixote", "Miguel Cervantes", "9789626345221"),
			new Book("Ulysses", "James Joyce", "9780394703800"),
			new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"),
			new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "9780060531041"),
			new Book("The adventures of Huckleberry Finn", "Mark Twain", "9781591940296"),
			new Book("Alice In Wonderland", "Lewis Carrol", "9780439291491")));

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#addBook(app.book.Book)
	 */
	@Override
	public boolean addBook(Book book) {
		return books.add(book);
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#getAllBooks()
	 */
	@Override
	public Iterable<Book> getAllBooks() {
		return books;
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#getBookByIsbn(java.lang.String)
	 */
	@Override
	public Book getBookByIsbn(String isbn) {
		return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#updateBookByIsbn(java.lang.String)
	 */
	@Override
	public Book updateBookByIsbn(Book book) throws BookException {
		try {
			if (book.getIsbn() == null) {
				throw new BookException("ID cannot be blank");
			}

			Book toEdit = filterBookByIsbn(book.getIsbn());

			if (toEdit == null) {
				throw new BookException("User not found");
			}

			if (book.getAuthor() != null) {
				toEdit.setAuthor(book.getAuthor());
			}
			if (book.getAuthor() != null) {
				toEdit.setAuthor(book.getAuthor());
			}
			if (book.getTitle() != null) {
				toEdit.setTitle(book.getTitle());
			}

			return toEdit;
		} catch (Exception ex) {
			throw new BookException(ex.getMessage());
		}
	}

	/**
	 * @param book
	 * @return
	 */
	private Book filterBookByIsbn(String isbn) {
		Book toEdit;
		toEdit = books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
		return toEdit;
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#deleteBookByIsbn(java.lang.String)
	 */
	@Override
	public boolean deleteBookByIsbn(String isbn) {
		return books.removeIf(b -> b.getIsbn().equals(isbn));
	}

	/*
	 * (non-Javadoc
	 * 
	 * @see app.book.BookDao#getRandomBook()
	 */
	@Override
	public Book getRandomBook() {
		return books.get(new Random().nextInt(books.size()));
	}

}
