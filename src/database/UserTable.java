package database;

import java.util.HashMap;
import java.util.Map;

import model.MyCalendar;
import model.User;

public class UserTable {
	
	//Map<User, Password>;
	private static Map<User, String> users = new HashMap<>();
	private User currentUser;

	private UserTable() {
		User user = new User("test", "test");
		users.put(user, "test");
		UserCalendarTable userCal = UserCalendarTable.getInstance();
		userCal.insertRecord(user, new MyCalendar());
		
	}
	private static UserTable instance = null;
	
	public static UserTable getInstance() {
		if(instance == null)
			instance = new UserTable();
		return instance;
	}
	
	private User getUserByEmail(String email) {
		if(users.size()>0) {
			for(User user:users.keySet()) {
				if(user.getEmail().equals(email))
					return user;
			}
		}
		return null;
	}
	public User isAuthenticated(String email, String password) 
	{
		User user = null;
		if(users.size()>0) {
			user = getUserByEmail(email);
			if(user!=null) {
				if(users.get(user).equals(password))
					return user;
				return null;
			}
		}
		return user;
	}
	public void insertRecord(User user, String password) {
		users.put(user, password);
	}
	public boolean removeRecord(User user) {
		if(users.remove(user)!=null)
			return true;
		return false;
	}

	public boolean isExistingUser(String email) {
		if(getUserByEmail(email)!=null) return true;
		return false;
	}
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	public User getCurrentUser() {
		return currentUser;
	}
}
