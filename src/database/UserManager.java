package database;

import exception.InvalidCredentials;
import exception.UserAlreadyExistsException;
import exception.UserDoesNotExistsException;
import model.User;

public class UserManager {
	UserAuthRepository userAuthRepository;

	public UserManager() {
		userAuthRepository = UserAuthRepository.getInstance();
	}

	public void signup(String name, String email, String password) throws UserAlreadyExistsException {
		if (isExistingUser(email))
			throw new UserAlreadyExistsException();	
		User newUser = new User(name, email, password);
		userAuthRepository.insertRecord(newUser);
	}

	public void login(String email, String password) throws InvalidCredentials, UserDoesNotExistsException {
		if (!isExistingUser(email))
			throw new UserDoesNotExistsException();
		User user = userAuthRepository.authenticate(email, password);
		if (user == null)
			throw new InvalidCredentials();
		userAuthRepository.setCurrentUser(user);
	}
	
	public boolean isExistingUser(String email) {
		return userAuthRepository.isExistingUser(email);
	}

	public User getLoggedInUser() {
		return userAuthRepository.getCurrentUser();// to-do handle not logged in state exception
	}

	public void logout() {
		userAuthRepository.setCurrentUser(null);// to-do handle already logout or not logged in exception
	}

}
