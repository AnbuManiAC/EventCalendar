package model;

public class User {
	private String name;
	private String email;
	private String password;
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;	
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
													
	public void setName(String name) {
		this.name = name;
	}
	              
	public String getEmail() {
		return email;
	}
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
}
                      