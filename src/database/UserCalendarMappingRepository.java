package database;

import java.util.HashMap;
import java.util.Map;

import model.MyCalendar;
import model.User;

public class UserCalendarMappingRepository {
	private static Map<User, CalendarEventRepository> userCalendar = new HashMap<>();
	private UserCalendarMappingRepository() {
		
	}
	
	private static UserCalendarMappingRepository instance = null;
	
	public static UserCalendarMappingRepository getInstance() {
		if(instance == null)
			instance = new UserCalendarMappingRepository();
		return instance;
	}	
	
	
	public void insertRecord(User user, MyCalendar calendar) {
		userCalendar.put(user, new CalendarEventRepository());
	}
	public CalendarEventRepository getUserCalendar(User user) {
		return userCalendar.get(user);
	}
	
}
