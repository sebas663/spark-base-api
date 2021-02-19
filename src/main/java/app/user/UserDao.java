package app.user;

public interface UserDao {

	public User getUserByUsername(String username);

	public Iterable<String> getAllUserNames();

}
