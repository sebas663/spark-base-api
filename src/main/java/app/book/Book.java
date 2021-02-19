package app.book;

public class Book {
	
	String title;
	String author;
	String isbn;

	public Book(String title, String author, String isbn) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getMediumCover() {
		return "http://covers.openlibrary.org/b/isbn/" + this.isbn + "-M.jpg";
	}

	public String getLargeCover() {
		return "http://covers.openlibrary.org/b/isbn/" + this.isbn + "-L.jpg";
	}
}
