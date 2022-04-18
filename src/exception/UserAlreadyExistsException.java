package exception;

public class UserAlreadyExistsException extends Exception {

	@Override
	public String getMessage() {
		return "Expected a user with unregistered email id. A user with same email is already registered";
	}
	
}
