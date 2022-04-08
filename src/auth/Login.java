package auth;

import database.UserAuthRepository;
import model.User;
import service.TestInterface;

public class Login {
	private String email;
	private String password;
	
	public Login(String email, String pwd, TestInterface test) {
		this.email = email;
		this.password = pwd;
		test.msg(pwd);
	}
	
	public boolean checkUser() {
		UserAuthRepository userAuth = UserAuthRepository.getInstance();
		User user = userAuth.isAuthenticated(email, password);
		if(user!=null) {
			userAuth.setCurrentUser(user);
			return true;
		}
		return false;
	}
		
}
