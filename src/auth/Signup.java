package auth;

import database.UserCalendarMappingRepository;
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
		storeNewUserCalendar(userCalendar);

	}
	
	private void storeNewUserCalendar(MyCalendar userCalendar) {
		UserCalendarMappingRepository userCal = UserCalendarMappingRepository.getInstance();
		userCal.insertRecord(newUser, userCalendar);
		
	}

	private void storeNewUser() {
		UserAuthRepository users = UserAuthRepository.getInstance();
		users.insertRecord(newUser, password);
	}
}
