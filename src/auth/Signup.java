package auth;

import database.UserAuthRepository;
import model.MyCalendar;
import model.User;

public class Signup {
	private User newUser;
	private String password;

	public Signup(User user, String password, MyCalendar userCalendar) {
		this.newUser = user;
		this.password = password;
		storeNewUser();
	}

	private void storeNewUser() {
		UserAuthRepository users = UserAuthRepository.getInstance();
		users.insertRecord(newUser, password);
	}
}
