package model;

public enum RecurrenceType {
	DAILY(1), WEEKLY(7);

	private int offset;

	private RecurrenceType(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

}
