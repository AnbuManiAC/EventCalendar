package exception;

public class InvalidInviteeException extends Exception {

	@Override
	public String getMessage() {
		return "Invalid invite! You can't invite yourself";
	}
	
}
