package database;

import java.util.HashMap;
import java.util.Map;

import model.MyCalendar;
import model.User;

public class UserCalendarTable {
	private static Map<User, MyCalendar> userCalendar = new HashMap<>();
	
	private UserCalendarTable() {
		
	}
	
	private static UserCalendarTable instance = null;
	
	public static UserCalendarTable getInstance() {
		if(instance == null)
			instance = new UserCalendarTable();
		return instance;
	}	
	
	public void insertRecord(User user, MyCalendar calendar) {
		userCalendar.put(user, calendar);
	}
	public MyCalendar getUserCelendar(User user) {
		return userCalendar.get(user);
	}
	
}
