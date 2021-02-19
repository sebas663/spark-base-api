package app.book;

public class BookDaoFactory {

	/**
	 * @return
	 */
	public static BookDao getBookDao() {
		return BookDaoImpl.getInstance();
	}
}
