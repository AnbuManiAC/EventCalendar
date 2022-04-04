package auth;

import database.UserTable;
import model.User;

public class Login {
	private String email;
	private String password;
	
	public Login(String email, String pwd) {
		this.email = email;
		this.password = pwd;
	}
	
	public boolean checkUser() {
		UserTable userAuth = UserTable.getInstance();
		User user = userAuth.isAuthenticated(email, password);
		if(user!=null) {
			userAuth.setCurrentUser(user);
			return true;
		}
		return false;
	}
		
}
