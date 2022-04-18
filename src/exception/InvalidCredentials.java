package exception;

public class InvalidCredentials extends Exception {

	@Override
	public String getMessage() {
		return "Email or password does not match!";
	}
	
}
