package exception;

public class InvalidEventException extends Exception {

	@Override
	public String getMessage() {
		return "Expected end date time to be after start date time";
	}
	
}
