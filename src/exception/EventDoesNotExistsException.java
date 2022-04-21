package exception;

public class EventDoesNotExistsException extends Exception {

	@Override
	public String getMessage() {
		return "Event with the id does not exists";
	}

}
