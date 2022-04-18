package exception;

public class NoUserLoggedIn extends Exception {

	@Override
	public String getMessage() {
		return "No user is logged in at this moment";
	}

}
