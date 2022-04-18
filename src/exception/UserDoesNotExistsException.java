package exception;

public class UserDoesNotExistsException extends Exception {

	@Override
	public String getMessage() {
		return "Expected a user with already registered email id. User with this email is not registered";
	}
	
}
