package auth;

import database.UserCalendarTable;
import database.UserTable;
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
		UserCalendarTable userCal = UserCalendarTable.getInstance();
		userCal.insertRecord(newUser, userCalendar);
		
	}

	private void storeNewUser() {
		UserTable users = UserTable.getInstance();
		users.insertRecord(newUser, password);
	}
}
