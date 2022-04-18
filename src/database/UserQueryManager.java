package database;

import exception.InvalidCredentials;
import exception.NoUserLoggedIn;
import exception.UserAlreadyExistsException;
import exception.UserDoesNotExistsException;
import model.User;

public class UserQueryManager {
	UserAuthRepository userAuthRepository;

	public UserQueryManager() {
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

	public User getLoggedInUser() throws NoUserLoggedIn {
		User user =  userAuthRepository.getCurrentUser();
		if(user==null) throw new NoUserLoggedIn();
		return user;
	}

	public void logout() {
		userAuthRepository.setCurrentUser(null);
	}

}
