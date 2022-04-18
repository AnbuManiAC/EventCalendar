package exception;

public class TimeParseException extends Exception {

	@Override
	public String getMessage() {
		return "Invalid time! Time cannot be parsed";
	}

}
