package database;

import java.util.HashSet;
import java.util.Set;

import model.User;

public class UserAuthRepository {

	private Set<User> users = new HashSet<>();
	private User currentUser;// May be we can maintain login state in HashMap by mapping user with boolean

	private UserAuthRepository() {
		currentUser = null;
		users.add(new User("test","test@gmail.com","Test@123"));
	}

	static UserAuthRepository instance = null;

	static UserAuthRepository getInstance() {
		if (instance == null)
			instance = new UserAuthRepository();
		return instance;
	}

	private User getUserByEmail(String email) {
		return users.stream().filter(user -> user.getEmail().equals(email)).findAny().orElse(null);
	}

	User authenticate(String email, String password) {
		return users.stream().filter(user -> user.getEmail().equals(email) && user.checkPassword(password)).findAny()
				.orElse(null);	
	}

	void insertRecord(User user) {
		users.add(user);
	}

	void removeRecord(User user) {
		users.remove(user);
	}

	void setCurrentUser(User user) {
		this.currentUser = user;
	}

	boolean isExistingUser(String email) {
		return getUserByEmail(email) != null;
	}

	User getCurrentUser() {
		return currentUser;
	}
}
